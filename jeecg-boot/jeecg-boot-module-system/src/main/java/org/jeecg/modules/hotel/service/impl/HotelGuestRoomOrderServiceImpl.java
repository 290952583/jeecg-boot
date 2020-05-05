package org.jeecg.modules.hotel.service.impl;

import org.checkerframework.checker.units.qual.A;
import org.jeecg.modules.hotel.entity.Hotel;
import org.jeecg.modules.hotel.entity.HotelGuestRoom;
import org.jeecg.modules.hotel.entity.HotelGuestRoomOrder;
import org.jeecg.modules.hotel.mapper.HotelGuestRoomMapper;
import org.jeecg.modules.hotel.mapper.HotelGuestRoomOrderMapper;
import org.jeecg.modules.hotel.service.IHotelGuestRoomOrderService;
import org.jeecg.modules.system.entity.SysAgent;
import org.jeecg.modules.system.entity.SysUserAgent;
import org.jeecg.modules.system.mapper.SysAgentMapper;
import org.jeecg.modules.system.mapper.SysUserAgentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 酒店客房订单表
 * @Author: jeecg-boot
 * @Date:   2020-05-04
 * @Version: V1.0
 */
@Service
public class HotelGuestRoomOrderServiceImpl extends ServiceImpl<HotelGuestRoomOrderMapper, HotelGuestRoomOrder> implements IHotelGuestRoomOrderService {

    @Autowired
    private HotelGuestRoomOrderMapper hotelGuestRoomOrderMapper;
    @Autowired
    private SysUserAgentMapper sysUserAgentMapper;
    @Autowired
    private HotelGuestRoomMapper hotelGuestRoomMapper;
    @Autowired
    private SysAgentMapper sysAgentMapper;

    @Override
    public void crateOrder(HotelGuestRoomOrder hotelGuestRoomOrder) {

        String roomId = hotelGuestRoomOrder.getHotelGuestRoomId();
        HotelGuestRoom room = hotelGuestRoomMapper.selectById(roomId);

        //抽取服务费
        ///获取父级代理商列表, 第一个获取
        List<SysAgent> agentList = sysAgentMapper.selectAgentByHotelId(room.getHotelId());

        //保存订单信息
        hotelGuestRoomOrderMapper.insert(hotelGuestRoomOrder);
    }
}
