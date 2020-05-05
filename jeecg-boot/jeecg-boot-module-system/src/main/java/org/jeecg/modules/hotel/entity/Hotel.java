package org.jeecg.modules.hotel.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.jeecg.common.system.base.entity.JeecgEntity;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 商家/酒店信息
 * @Author: jeecg-boot
 * @Date:   2020-05-04
 * @Version: V1.0
 */
@ApiModel(value="hotel对象", description="商家/酒店信息")
@Data
@TableName("hotel")
public class Hotel extends JeecgEntity implements Serializable {
    private static final long serialVersionUID = 1L;

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
	/**法人照片*/
	@Excel(name = "法人照片", width = 15)
    @ApiModelProperty(value = "法人照片")
    private String corporatePhotos;
	/**营业执照*/
	@Excel(name = "营业执照", width = 15)
    @ApiModelProperty(value = "营业执照")
    private String businessLicense;
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
    /**酒店的状态（0：未审核，1：已审核，2：已冻结）*/
    @Excel(name = "酒店的状态（0：未审核，1：已审核，2：已冻结, 3：未营业）", width = 15)
    @ApiModelProperty(value = "酒店的状态（0：未审核，1：已审核，2：已冻结）")
    private java.lang.Integer status;


    public enum HotelStatus {
        WAIT_EXAMINE(0, "待审核"),
        ALREADY_EXAMINE(1, "已审核"),
        ALREADY_FROZE(2, "已冻结"),
        UN_OPEN(2, "未营业");

        private Integer status;

        private String name;

        HotelStatus(Integer status, String name) {
            this.status = status;
            this.name = name;
        }

        public Integer getStatus() {
            return status;
        }

        public String getName() {
            return name;
        }

        public static HotelStatus valueByStatus(Integer in){
            for(HotelStatus status : HotelStatus.values()){
                if(status.status == in){
                    return status;
                }
            }

            throw new IllegalArgumentException("找不到状态：" + in);
        }
    }
}
