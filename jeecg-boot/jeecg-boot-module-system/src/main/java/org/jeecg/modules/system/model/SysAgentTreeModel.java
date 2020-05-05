package org.jeecg.modules.system.model;

import org.apache.commons.lang.StringUtils;
import org.jeecg.common.system.vo.SysAgentModel;
import org.jeecg.modules.system.entity.SysAgent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 代理商表 存储树结构数据的实体类
 * <p>
 * 
 * @Author Steve
 * @Since 2019-01-22 
 */
public class SysAgentTreeModel implements Serializable{


    private static final long serialVersionUID = -7044587321443649767L;
    /** 对应sysAgent中的id字段,前端数据树中的key*/
    private String key;

    /** 对应sysAgent中的id字段,前端数据树中的value*/
    private String value;

    /** 对应depart_name字段,前端数据树中的title*/
    private String title;


    private boolean isLeaf;
    // 以下所有字段均与sysAgent相同

    private String id;

    private String parentId;

    private Integer areaId;

    private Integer areaCode;

    private String areaName;

    private String agentName;

    private String agentNameEn;

    private String agentNameAbbr;

    private Integer level;

    private Integer agentOrder;

    private Object description;

    private String mobile;

    private String address;

    private String status;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private List<SysAgentTreeModel> children = new ArrayList<>();


    /**
     * 将sysAgent对象转换成sysAgentTreeModel对象
     * @param sysAgent
     */
	public SysAgentTreeModel(SysAgentModel sysAgent) {
		this.key = sysAgent.getId();
        this.value = sysAgent.getId();
        this.title = sysAgent.getAgentName();
        this.id = sysAgent.getId();
        this.parentId = sysAgent.getParentId();
        this.agentName = sysAgent.getAgentName();
        this.agentNameEn = sysAgent.getAgentNameEn();
        this.agentNameAbbr = sysAgent.getAgentNameAbbr();
        this.agentOrder = sysAgent.getAgentOrder();
        this.description = sysAgent.getDescription();
        this.mobile = sysAgent.getMobile();
        this.address = sysAgent.getAddress();
        this.status = sysAgent.getStatus();
    }

    public boolean getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(boolean isleaf) {
         this.isLeaf = isleaf;
    }

    public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SysAgentTreeModel> getChildren() {
        return children;
    }

    public void setChildren(List<SysAgentTreeModel> children) {
        if (children==null){
            this.isLeaf=true;
        }
        this.children = children;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public SysAgentTreeModel() { }

    /**
     * 重写equals方法
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
			return true;
		}
        if (o == null || getClass() != o.getClass()) {
			return false;
		}
        SysAgentTreeModel model = (SysAgentTreeModel) o;
        return Objects.equals(areaId, model.areaId) &&
                Objects.equals(agentName, model.agentName);
    }
    
    /**
     * 重写hashCode方法
     */
    @Override
    public int hashCode() {

        return Objects.hash( areaId, agentName);
    }

}
