package org.jeecg.modules.hotel.vo;

import java.util.List;
import org.jeecg.modules.hotel.entity.Hotel;
import org.jeecg.modules.hotel.entity.HotelGuestRoom;
import org.jeecg.modules.hotel.entity.HotelAttachment;
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
 * @Description: 商家/酒店信息
 * @Author: jeecg-boot
 * @Date:   2020-05-04
 * @Version: V1.0
 */
@Data
@ApiModel(value="hotelPage对象", description="商家/酒店信息")
public class HotelPage {
	
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
	/**商家名称*/
	@Excel(name = "商家名称", width = 15)
	@ApiModelProperty(value = "商家名称")
	private String name;
	/**余额*/
	@Excel(name = "余额", width = 15)
	@ApiModelProperty(value = "余额")
	private java.math.BigDecimal balance;
	/**等级*/
	@Excel(name = "等级", width = 15)
	@ApiModelProperty(value = "等级")
	private Integer level;
	/**地址*/
	@Excel(name = "地址", width = 15)
	@ApiModelProperty(value = "地址")
	private String address;
	/**电话*/
	@Excel(name = "电话", width = 15)
	@ApiModelProperty(value = "电话")
	private String telephone;
	/**描述*/
	@Excel(name = "描述", width = 15)
	@ApiModelProperty(value = "描述")
	private String description;
	/**类型*/
	@Excel(name = "类型", width = 15)
	@ApiModelProperty(value = "类型")
	private String type;
	/**酒店简介*/
	@Excel(name = "酒店简介", width = 15)
	@ApiModelProperty(value = "酒店简介")
	private String introduction;
	/**酒店政策*/
	@Excel(name = "酒店政策", width = 15)
	@ApiModelProperty(value = "酒店政策")
	private String policy;
	/**银行账户*/
	@Excel(name = "银行账户", width = 15)
	@ApiModelProperty(value = "银行账户")
	private String bankAccount;
	/**开户银行*/
	@Excel(name = "开户银行", width = 15)
	@ApiModelProperty(value = "开户银行")
	private String bankName;
	/**服务费率*/
	@Excel(name = "服务费率", width = 15)
	@ApiModelProperty(value = "服务费率")
	private java.math.BigDecimal serviceRate;
	/**提示信息*/
	@Excel(name = "提示信息", width = 15)
	@ApiModelProperty(value = "提示信息")
	private String poinInfo;
	/**地理位置*/
	@Excel(name = "地理位置", width = 15)
	@ApiModelProperty(value = "地理位置")
	private String position;
	
	@ExcelCollection(name="酒店客房信息")
	@ApiModelProperty(value = "酒店客房信息")
	private List<HotelGuestRoom> hotelGuestRoomList;
	@ExcelCollection(name="酒店上传的附件(图片)信息")
	@ApiModelProperty(value = "酒店上传的附件(图片)信息")
	private List<HotelAttachment> hotelAttachmentList;
	
}
