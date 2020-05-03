package org.jeecg.modules.hotel.service.impl;

import org.jeecg.modules.hotel.entity.HotelGuestRoom;
import org.jeecg.modules.hotel.mapper.HotelGuestRoomMapper;
import org.jeecg.modules.hotel.service.IHotelGuestRoomService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

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
	
	@Override
	public List<HotelGuestRoom> selectByMainId(String mainId) {
		return hotelGuestRoomMapper.selectByMainId(mainId);
	}
}
