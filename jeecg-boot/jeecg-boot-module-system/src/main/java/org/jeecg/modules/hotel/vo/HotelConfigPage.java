package org.jeecg.modules.hotel.vo;

import java.util.List;
import org.jeecg.modules.hotel.entity.HotelConfig;
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
 * @Description: 商品的客房配置主表
 * @Author: jeecg-boot
 * @Date:   2020-05-04
 * @Version: V1.0
 */
@Data
@ApiModel(value="hotel_configPage对象", description="商品的客房配置主表")
public class HotelConfigPage {
	
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
	/**删除的时间戳*/
	@Excel(name = "删除的时间戳", width = 15)
	@ApiModelProperty(value = "删除的时间戳")
	private Integer deletedTime;
	/**配置名称*/
	@Excel(name = "配置名称", width = 15)
	@ApiModelProperty(value = "配置名称")
	private String name;
	/**配置描述*/
	@Excel(name = "配置描述", width = 15)
	@ApiModelProperty(value = "配置描述")
	private String description;
	/**配置的类型（0：酒店，1：客房））*/
	@Excel(name = "配置的类型（0：酒店，1：客房））", width = 15)
	@ApiModelProperty(value = "配置的类型（0：酒店，1：客房））")
	private Integer type;
	/**显示类型（0：只显示，1：带跳转的）*/
	@Excel(name = "显示类型（0：只显示，1：带跳转的）", width = 15)
	@ApiModelProperty(value = "显示类型（0：只显示，1：带跳转的）")
	private Integer typeShow;
	/**图标*/
	@Excel(name = "图标", width = 15)
	@ApiModelProperty(value = "图标")
	private String iconUrl;
	/**跳转的功能url*/
	@Excel(name = "跳转的功能url", width = 15)
	@ApiModelProperty(value = "跳转的功能url")
	private String configUrl;
	/**酒店的id*/
	@Excel(name = "酒店的id", width = 15)
	@ApiModelProperty(value = "酒店的id")
	private String hotelId;
	
	
}
