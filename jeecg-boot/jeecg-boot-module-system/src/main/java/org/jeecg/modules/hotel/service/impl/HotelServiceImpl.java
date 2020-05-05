package org.jeecg.modules.hotel.service.impl;

import io.swagger.models.auth.In;
import org.checkerframework.checker.units.qual.A;
import org.jeecg.modules.hotel.entity.Hotel;
import org.jeecg.modules.hotel.entity.HotelGuestRoom;
import org.jeecg.modules.hotel.entity.HotelAttachment;
import org.jeecg.modules.hotel.entity.HotelGuestRoomConfig;
import org.jeecg.modules.hotel.mapper.HotelGuestRoomConfigMapper;
import org.jeecg.modules.hotel.mapper.HotelGuestRoomMapper;
import org.jeecg.modules.hotel.mapper.HotelAttachmentMapper;
import org.jeecg.modules.hotel.mapper.HotelMapper;
import org.jeecg.modules.hotel.service.IHotelService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Description: 商家/酒店信息
 * @Author: jeecg-boot
 * @Date: 2020-05-04
 * @Version: V1.0
 */
@Service
public class HotelServiceImpl extends ServiceImpl<HotelMapper, Hotel> implements IHotelService {

    @Autowired
    private HotelMapper hotelMapper;
    @Autowired
    private HotelGuestRoomMapper hotelGuestRoomMapper;
    @Autowired
    private HotelAttachmentMapper hotelAttachmentMapper;
    @Autowired
    private HotelGuestRoomConfigMapper hoteGuestRoomConfigMapper;

    @Override
    @Transactional
    public void saveMain(Hotel hotel, Integer floors, Integer rooms, BigDecimal price, List<String> configIds, List<HotelGuestRoom> hotelGuestRoomList, List<HotelAttachment> hotelAttachmentList) {

        /*酒店信息*/
        hotel.setId(UUID.randomUUID().toString());
        hotel.setDefaultValue();
        hotelMapper.insert(hotel);

        // 插入房间列表
        if (hotelGuestRoomList != null && hotelGuestRoomList.size() > 0) {
            for (HotelGuestRoom entity : hotelGuestRoomList) {
                //外键设置
                entity.setHotelId(hotel.getId());
                hotelGuestRoomMapper.insert(entity);
            }
        }

        //客房信息 初始化批量插入 初始化 x 楼 y 个房间
        if (floors != null && rooms != null) {
            if (ObjectUtils.isEmpty(hotelGuestRoomList)) {
                hotelGuestRoomList = new ArrayList<>();
            } else {
                hotelGuestRoomList.clear();
            }

            List<HotelGuestRoomConfig> configList = new ArrayList<>();

            for (int i = 0; i < floors; i++) {
                for (int j = 0; j < rooms; j++) {
                    HotelGuestRoom room = new HotelGuestRoom();
                    room.setId(UUID.randomUUID().toString());
                    room.setHotelId(hotel.getId());
                    String roomName = (i + 1) + frontCompWithZero((j + 1), 2);
                    room.setName(roomName);
                    room.setStandardPrice(price);
                    room.setDefaultValue();
                    hotelGuestRoomList.add(room);

                    if(ObjectUtils.isEmpty(configIds)){
                        configIds.forEach(configId ->{
                            HotelGuestRoomConfig config = new HotelGuestRoomConfig();
                            config.setId(UUID.randomUUID().toString());
                            config.setConfigId(configId);
                            config.setHotelGuestRoomId(room.getId());
                            config.setDefaultValue();
                        });
                    }
                }
            }

            //批量保存客房
            hotelGuestRoomMapper.batchInsert(hotelGuestRoomList);
            if(ObjectUtils.isEmpty(configList)){
                //批量保存客房配置
                hoteGuestRoomConfigMapper.batchInsert(configList);
            }
        }

        //附件信息：法人照片、营业执照
        if (hotelAttachmentList != null && hotelAttachmentList.size() > 0) {
            for (HotelAttachment entity : hotelAttachmentList) {
                //外键设置
                entity.setHotelId(hotel.getId());
                hotelAttachmentMapper.insert(entity);
            }
        }
    }

    @Override
    @Transactional
    public void updateMain(Hotel hotel, List<HotelGuestRoom> hotelGuestRoomList, List<HotelAttachment> hotelAttachmentList) {
        hotelMapper.updateById(hotel);

        //1.先删除子表数据
        hotelGuestRoomMapper.deleteByMainId(hotel.getId());
        hotelAttachmentMapper.deleteByMainId(hotel.getId());

        //2.子表数据重新插入
        if (hotelGuestRoomList != null && hotelGuestRoomList.size() > 0) {
            for (HotelGuestRoom entity : hotelGuestRoomList) {
                //外键设置
                entity.setHotelId(hotel.getId());
                hotelGuestRoomMapper.insert(entity);
            }
        }
        if (hotelAttachmentList != null && hotelAttachmentList.size() > 0) {
            for (HotelAttachment entity : hotelAttachmentList) {
                //外键设置
                entity.setHotelId(hotel.getId());
                hotelAttachmentMapper.insert(entity);
            }
        }
    }

    @Override
    @Transactional
    public void delMain(String id) {
        hotelGuestRoomMapper.deleteByMainId(id);
        hotelAttachmentMapper.deleteByMainId(id);
        hotelMapper.deleteById(id);
    }

    @Override
    @Transactional
    public void delBatchMain(Collection<? extends Serializable> idList) {
        for (Serializable id : idList) {
            hotelGuestRoomMapper.deleteByMainId(id.toString());
            hotelAttachmentMapper.deleteByMainId(id.toString());
            hotelMapper.deleteById(id);
        }
    }

    @Override
    public void updateStatus(String hotelId, Integer status) {
        hotelMapper.updateStatusById(hotelId, status);
    }


    /**
 　　* 将元数据前补零，补后的总长度为指定的长度，以字符串的形式返回
 　　* @param sourceDate
 　　* @param formatLength
 　　* @return 重组后的数据
 　　*/
    private String frontCompWithZero(int sourceDate, int formatLength) {
        /*
         * 0 指前面补充零
         * formatLength 字符总长度为 formatLength
         * d 代表为正数。
         */
        String newString = String.format("%0" + formatLength + "d", sourceDate);
        return newString;
    }
}
