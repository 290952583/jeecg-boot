package org.jeecg.modules.hotel.service.impl;

import org.jeecg.modules.hotel.entity.HotelGuestRoomConfig;
import org.jeecg.modules.hotel.mapper.HotelGuestRoomConfigMapper;
import org.jeecg.modules.hotel.service.IHotelGuestRoomConfigService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 酒店客房的配置
 * @Author: jeecg-boot
 * @Date:   2020-05-04
 * @Version: V1.0
 */
@Service
public class HotelGuestRoomConfigServiceImpl extends ServiceImpl<HotelGuestRoomConfigMapper, HotelGuestRoomConfig> implements IHotelGuestRoomConfigService {
	
	@Autowired
	private HotelGuestRoomConfigMapper hotelGuestRoomConfigMapper;
	
	@Override
	public List<HotelGuestRoomConfig> selectByMainId(String mainId) {
		return hotelGuestRoomConfigMapper.selectByMainId(mainId);
	}
}
