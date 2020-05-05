package org.jeecg.modules.hotel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.hotel.entity.HotelGuestRoom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 酒店客房信息
 * @Author: jeecg-boot
 * @Date:   2020-05-04
 * @Version: V1.0
 */
public interface HotelGuestRoomMapper extends BaseMapper<HotelGuestRoom> {

    void deleteByMainId(String id);

    void batchInsert(List<HotelGuestRoom> list);
}
