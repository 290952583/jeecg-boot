package org.jeecg.modules.hotel.entity;

import java.io.Serializable;
import java.util.Date;
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

/**
 * @Description: 酒店客房信息
 * @Author: jeecg-boot
 * @Date:   2020-05-04
 * @Version: V1.0
 */
@ApiModel(value="hotel_guest_room对象", description="酒店客房信息")
@Data
@TableName("hotel_guest_room")
public class HotelGuestRoom extends JeecgEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**酒店id*/
	@Excel(name = "酒店id", width = 15)
    @ApiModelProperty(value = "酒店id")
    private String hotelId;
	/**客房名称*/
	@Excel(name = "客房名称", width = 15)
    @ApiModelProperty(value = "客房名称")
    private String name;
	/**标准价*/
	@Excel(name = "标准价", width = 15)
    @ApiModelProperty(value = "标准价")
    private java.math.BigDecimal standardPrice;
	/**促销价*/
	@Excel(name = "促销价", width = 15)
    @ApiModelProperty(value = "促销价")
    private java.math.BigDecimal salePrice;
}
