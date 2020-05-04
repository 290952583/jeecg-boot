package org.jeecg.modules.hotel.vo;

import java.util.List;
import org.jeecg.modules.hotel.entity.HotelGuestRoom;
import org.jeecg.modules.hotel.entity.HotelGuestRoomConfig;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 酒店客房信息
 * @Author: jeecg-boot
 * @Date:   2020-05-04
 * @Version: V1.0
 */
@Data
@ApiModel(value="hotel_guest_roomPage对象", description="酒店客房信息")
public class HotelGuestRoomPage {
	
	/**主键*/
	@ApiModelProperty(value = "主键")
	private String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
	@ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建日期")
	private Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
	@ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "更新日期")
	private Date updateTime;
	/**所属部门*/
	@Excel(name = "所属部门", width = 15)
	@ApiModelProperty(value = "所属部门")
	private String sysOrgCode;
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
	
	@ExcelCollection(name="酒店客房的配置")
	@ApiModelProperty(value = "酒店客房的配置")
	private List<HotelGuestRoomConfig> hotelGuestRoomConfigList;
	
}
