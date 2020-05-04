package org.jeecg.modules.hotel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.hotel.entity.Hotel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 商家/酒店信息
 * @Author: jeecg-boot
 * @Date:   2020-05-04
 * @Version: V1.0
 */
public interface HotelMapper extends BaseMapper<Hotel> {

    /**
     * 修改酒店状态
     * @param hotelId
     * @param status
     */
    void updateStatusById(String hotelId, Integer status);
}
