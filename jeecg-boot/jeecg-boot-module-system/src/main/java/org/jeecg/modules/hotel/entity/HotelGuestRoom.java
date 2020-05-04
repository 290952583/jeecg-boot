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
 * @Description: 酒店客房信息
 * @Author: jeecg-boot
 * @Date:   2020-05-04
 * @Version: V1.0
 */
@ApiModel(value="hotel对象", description="商家/酒店信息")
@Data
@TableName("hotel_guest_room")
public class HotelGuestRoom extends JeecgEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*//**//*
	@TableId(type = IdType.ID_WORKER_STR)
	@ApiModelProperty(value = "主键")
	private String id;
	/**创建人*//*
	@Excel(name = "创建人", width = 15)
	@ApiModelProperty(value = "创建人")
	private String createBy;
	*//**创建日期*//*
	@Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建日期")
	private Date createTime;
	*//**更新人*//*
	@Excel(name = "更新人", width = 15)
	@ApiModelProperty(value = "更新人")
	private String updateBy;
	*//**更新日期*//*
	@Excel(name = "更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "更新日期")
	private Date updateTime;
	*//**所属部门*//*
	@Excel(name = "所属部门", width = 15)
	@ApiModelProperty(value = "所属部门")
	private String sysOrgCode;*/

	/**酒店id*/
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
