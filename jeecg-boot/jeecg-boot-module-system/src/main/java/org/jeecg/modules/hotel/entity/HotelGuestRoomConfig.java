package org.jeecg.modules.hotel.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.jeecg.common.system.base.entity.JeecgEntity;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 酒店客房的配置
 * @Author: jeecg-boot
 * @Date:   2020-05-04
 * @Version: V1.0
 */
@ApiModel(value="hotel_guest_room对象", description="酒店客房信息")
@Data
@TableName("hotel_guest_room_config")
public class HotelGuestRoomConfig extends JeecgEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**配置的id*/
	@Excel(name = "配置的id", width = 15)
	@ApiModelProperty(value = "配置的id")
	private String configId;
	/**客房的id*/
	@ApiModelProperty(value = "客房的id")
	private String hotelGuestRoomId;
}
