package org.jeecg.modules.hotel.service.impl;

import org.jeecg.modules.hotel.entity.HotelConfig;
import org.jeecg.modules.hotel.mapper.HotelConfigMapper;
import org.jeecg.modules.hotel.service.IHotelConfigService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 商品的客房配置主表
 * @Author: jeecg-boot
 * @Date:   2020-05-04
 * @Version: V1.0
 */
@Service
public class HotelConfigServiceImpl extends ServiceImpl<HotelConfigMapper, HotelConfig> implements IHotelConfigService {

	@Autowired
	private HotelConfigMapper hotelConfigMapper;

	
}
