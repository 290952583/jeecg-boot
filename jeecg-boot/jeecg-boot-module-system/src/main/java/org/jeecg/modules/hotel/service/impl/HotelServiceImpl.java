package org.jeecg.modules.hotel.service.impl;

import io.swagger.models.auth.In;
import org.jeecg.modules.hotel.entity.Hotel;
import org.jeecg.modules.hotel.entity.HotelGuestRoom;
import org.jeecg.modules.hotel.entity.HotelAttachment;
import org.jeecg.modules.hotel.mapper.HotelGuestRoomMapper;
import org.jeecg.modules.hotel.mapper.HotelAttachmentMapper;
import org.jeecg.modules.hotel.mapper.HotelMapper;
import org.jeecg.modules.hotel.service.IHotelService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Collection;

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

    @Override
    @Transactional
    public void saveMain(Hotel hotel, List<HotelGuestRoom> hotelGuestRoomList, List<HotelAttachment> hotelAttachmentList) {

    	/*酒店信息*/
        hotelMapper.insert(hotel);
        //客房信息 初始化 x 楼 y 个房间
        if (hotelGuestRoomList != null && hotelGuestRoomList.size() > 0) {
            for (HotelGuestRoom entity : hotelGuestRoomList) {
                //外键设置
                entity.setHotelId(hotel.getId());
                hotelGuestRoomMapper.insert(entity);
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

}
