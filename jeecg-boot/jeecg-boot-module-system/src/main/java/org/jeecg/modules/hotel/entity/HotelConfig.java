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
 * @Description: 商品的客房配置主表
 * @Author: jeecg-boot
 * @Date:   2020-05-04
 * @Version: V1.0
 */
@ApiModel(value="hotel_config对象", description="商品的客房配置主表")
@Data
@TableName("hotel_config")
public class HotelConfig extends JeecgEntity implements Serializable {
    private static final long serialVersionUID = 1L;

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
	@Excel(name = "图标上传文件的id", width = 15)
    @ApiModelProperty(value = "图标")
    private String attachmentId;
	/**跳转的功能url*/
	@Excel(name = "跳转的功能url", width = 15)
    @ApiModelProperty(value = "跳转的功能url")
    private String configUrl;
	/**酒店的id*/
	@Excel(name = "酒店的id", width = 15)
    @ApiModelProperty(value = "酒店的id")
    private String hotelId;
}
