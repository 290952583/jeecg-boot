package org.jeecg.modules.hotel.service;

import org.jeecg.modules.hotel.entity.HotelGuestRoom;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 酒店客房信息
 * @Author: jeecg-boot
 * @Date:   2020-05-04
 * @Version: V1.0
 */
public interface IHotelGuestRoomService extends IService<HotelGuestRoom> {

	public List<HotelGuestRoom> selectByMainId(String mainId);
}
