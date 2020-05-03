package org.jeecg.modules.hotel.service;

import org.jeecg.modules.hotel.entity.HotelGuestRoom;
import org.jeecg.modules.hotel.entity.Hotel;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 商家/酒店信息
 * @Author: jeecg-boot
 * @Date:   2020-05-04
 * @Version: V1.0
 */
public interface IHotelService extends IService<Hotel> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(Hotel hotel, List<HotelGuestRoom> hotelGuestRoomList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(Hotel hotel, List<HotelGuestRoom> hotelGuestRoomList);
	
	/**
	 * 删除一对多
	 */
	public void delMain(String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);
	
}
