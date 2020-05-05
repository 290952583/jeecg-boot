package org.jeecg.modules.hotel.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.jeecg.common.system.base.entity.JeecgEntity;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 酒店客房订单表
 * @Author: jeecg-boot
 * @Date:   2020-05-05
 * @Version: V1.0
 */
@Data
@TableName("hotel_guest_room_order")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="hotel_guest_room_order对象", description="酒店客房订单表")
public class HotelGuestRoomOrder extends JeecgEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**下单时间*/
	@Excel(name = "下单时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "下单时间")
    private Date orderTime;
	/**订单状态（0：已下顶单，1：订单已取房，2：订单已完成）*/
	@Excel(name = "订单状态（0：已下顶单，1：订单已取房，2：订单已完成）", width = 15)
    @ApiModelProperty(value = "订单状态（0：已下顶单，1：订单已取房，2：订单已完成）")
    private Integer status;
	/**订单价格*/
	@Excel(name = "订单价格", width = 15)
    @ApiModelProperty(value = "订单价格")
    private BigDecimal orderPrice;
	/**取房时间*/
	@Excel(name = "取房时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "取房时间")
    private Date grantTime;
	/**退房时间*/
	@Excel(name = "退房时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "退房时间")
    private Date retreatTime;
	/**完成时间*/
	@Excel(name = "完成时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "完成时间")
    private Date finishTime;
	/**收取的手续费*/
	@Excel(name = "收取的手续费", width = 15)
    @ApiModelProperty(value = "收取的手续费")
    private BigDecimal serviceCharge;
	/**客房id*/
	@Excel(name = "客房id", width = 15)
    @ApiModelProperty(value = "客房id")
    private String hotelGuestRoomId;
}
