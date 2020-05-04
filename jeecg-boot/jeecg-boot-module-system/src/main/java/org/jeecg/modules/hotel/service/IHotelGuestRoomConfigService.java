package org.jeecg.modules.hotel.service;

import org.jeecg.modules.hotel.entity.HotelGuestRoomConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 酒店客房的配置
 * @Author: jeecg-boot
 * @Date:   2020-05-04
 * @Version: V1.0
 */
public interface IHotelGuestRoomConfigService extends IService<HotelGuestRoomConfig> {

	public List<HotelGuestRoomConfig> selectByMainId(String mainId);
}
