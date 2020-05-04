package org.jeecg.modules.hotel.service;

import org.jeecg.modules.hotel.entity.HotelGuestRoomConfig;
import org.jeecg.modules.hotel.entity.HotelGuestRoom;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 酒店客房信息
 * @Author: jeecg-boot
 * @Date:   2020-05-04
 * @Version: V1.0
 */
public interface IHotelGuestRoomService extends IService<HotelGuestRoom> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(HotelGuestRoom hotelGuestRoom, List<HotelGuestRoomConfig> hotelGuestRoomConfigList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(HotelGuestRoom hotelGuestRoom, List<HotelGuestRoomConfig> hotelGuestRoomConfigList);
	
	/**
	 * 删除一对多
	 */
	public void delMain(String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);

	/**
	 * 查询
	 * @param id
	 * @return
	 */
    List<HotelGuestRoom> selectByMainId(String id);
}
