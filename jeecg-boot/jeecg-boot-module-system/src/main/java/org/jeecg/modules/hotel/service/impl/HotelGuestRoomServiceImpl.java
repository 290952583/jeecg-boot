package org.jeecg.modules.hotel.service.impl;

import org.jeecg.modules.hotel.entity.HotelGuestRoom;
import org.jeecg.modules.hotel.entity.HotelGuestRoomConfig;
import org.jeecg.modules.hotel.mapper.HotelGuestRoomConfigMapper;
import org.jeecg.modules.hotel.mapper.HotelGuestRoomMapper;
import org.jeecg.modules.hotel.service.IHotelGuestRoomService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 酒店客房信息
 * @Author: jeecg-boot
 * @Date:   2020-05-04
 * @Version: V1.0
 */
@Service
public class HotelGuestRoomServiceImpl extends ServiceImpl<HotelGuestRoomMapper, HotelGuestRoom> implements IHotelGuestRoomService {

	@Autowired
	private HotelGuestRoomMapper hotelGuestRoomMapper;
	@Autowired
	private HotelGuestRoomConfigMapper hotelGuestRoomConfigMapper;
	
	@Override
	@Transactional
	public void saveMain(HotelGuestRoom hotelGuestRoom, List<HotelGuestRoomConfig> hotelGuestRoomConfigList) {
		hotelGuestRoomMapper.insert(hotelGuestRoom);
		if(hotelGuestRoomConfigList!=null && hotelGuestRoomConfigList.size()>0) {
			for(HotelGuestRoomConfig entity:hotelGuestRoomConfigList) {
				//外键设置
				entity.setHotelGuestRoomId(hotelGuestRoom.getId());
				hotelGuestRoomConfigMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(HotelGuestRoom hotelGuestRoom,List<HotelGuestRoomConfig> hotelGuestRoomConfigList) {
		hotelGuestRoomMapper.updateById(hotelGuestRoom);
		
		//1.先删除子表数据
		hotelGuestRoomConfigMapper.deleteByMainId(hotelGuestRoom.getId());
		
		//2.子表数据重新插入
		if(hotelGuestRoomConfigList!=null && hotelGuestRoomConfigList.size()>0) {
			for(HotelGuestRoomConfig entity:hotelGuestRoomConfigList) {
				//外键设置
				entity.setHotelGuestRoomId(hotelGuestRoom.getId());
				hotelGuestRoomConfigMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		hotelGuestRoomConfigMapper.deleteByMainId(id);
		hotelGuestRoomMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			hotelGuestRoomConfigMapper.deleteByMainId(id.toString());
			hotelGuestRoomMapper.deleteById(id);
		}
	}

    @Override
    public List<HotelGuestRoom> selectByMainId(String id) {
        return null;
    }

}
