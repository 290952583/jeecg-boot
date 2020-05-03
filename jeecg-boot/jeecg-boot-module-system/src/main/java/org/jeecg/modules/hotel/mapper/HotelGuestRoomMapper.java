package org.jeecg.modules.hotel.mapper;

import java.util.List;
import org.jeecg.modules.hotel.entity.HotelGuestRoom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 酒店客房信息
 * @Author: jeecg-boot
 * @Date:   2020-05-04
 * @Version: V1.0
 */
public interface HotelGuestRoomMapper extends BaseMapper<HotelGuestRoom> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<HotelGuestRoom> selectByMainId(@Param("mainId") String mainId);
}
