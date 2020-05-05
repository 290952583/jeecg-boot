package org.jeecg.common.system.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 代理商model
 */
public class SysAgentModel {
    /**ID*/
    @ApiModelProperty(value = "代理商id")
    private String id;
    /**区域id*/
    @ApiModelProperty(value = "区域id")
    private Integer areaId;
    /**区域编码*/
    @ApiModelProperty(value = "区域编码")
    private String areaCode;
    /**区域名称*/
    @ApiModelProperty(value = "区域名称")
    private String areaName;
    /**父机构ID*/
    @ApiModelProperty(value = "父机构ID")
    private String parentId;
    /**代理商名称*/
    @ApiModelProperty(value = "代理商名称")
    private String agentName;
    /**英文名*/
    @ApiModelProperty(value = "代理商英文名")
    private String agentNameEn;
    /**缩写*/
    @ApiModelProperty(value = "代理商缩写")
    private String agentNameAbbr;
    /** 等级*/
    @ApiModelProperty(value = "等级（1省级代理；2市级代理；3区县代理）")
    private Integer level;
    /**排序*/
    @ApiModelProperty(value = "排序")
    private Integer agentOrder;
    /**描述*/
    @ApiModelProperty(value = "描述")
    private Object description;
    /**手机号*/
    @ApiModelProperty(value = "手机号")
    private String mobile;
    /**地址*/
    @ApiModelProperty(value = "地址")
    private String address;
    /**备注*/
    @ApiModelProperty(value = "备注")
    private String remark;
    /**状态*/
    @ApiModelProperty(value = "状态")
    private String status;
    /**用户账号/用户名*/
    @ApiModelProperty(value = "用户账号/用户名")
    private String userName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentNameEn() {
        return agentNameEn;
    }

    public void setAgentNameEn(String agentNameEn) {
        this.agentNameEn = agentNameEn;
    }

    public String getAgentNameAbbr() {
        return agentNameAbbr;
    }

    public void setAgentNameAbbr(String agentNameAbbr) {
        this.agentNameAbbr = agentNameAbbr;
    }

    public Integer getAgentOrder() {
        return agentOrder;
    }

    public void setAgentOrder(Integer agentOrder) {
        this.agentOrder = agentOrder;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
