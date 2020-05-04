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
 * @Description: 酒店上传的附件(图片)信息
 * @Author: jeecg-boot
 * @Date:   2020-05-04
 * @Version: V1.0
 */
@ApiModel(value="hotel对象", description="商家/酒店信息")
@Data
@TableName("hotel_attachment")
public class HotelAttachment extends JeecgEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**酒店id*/
	@ApiModelProperty(value = "酒店id")
	private String hotelId;
	/**附件表的id*/
	@Excel(name = "附件表的id", width = 15)
	@ApiModelProperty(value = "附件表的id")
	private String attachmentId;
	/**附件的类型（0：法人照片，1：营业执照）*/
	@Excel(name = "附件的类型（0：法人照片，1：营业执照）", width = 15)
	@ApiModelProperty(value = "附件的类型（0：法人照片，1：营业执照）")
	private Integer attachmentType;

	/** 排序 */
	@Excel(name = "排序", width = 15)
	@ApiModelProperty(value = "排序")
	private Integer sort;
}
