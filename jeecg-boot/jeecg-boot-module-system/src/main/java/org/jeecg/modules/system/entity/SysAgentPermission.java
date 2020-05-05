package org.jeecg.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 代理商权限表
 * @Author: jeecg-boot
 * @Date:   2020-05-05
 * @Version: V1.0
 */
@Data
@TableName("sys_agent_permission")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="sys_agent_permission对象", description="代理商权限表")
public class SysAgentPermission {

	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
	private String id;
	/**代理商id*/
	@Excel(name = "代理商id", width = 15)
    @ApiModelProperty(value = "代理商id")
	private String agentId;
	/**权限id*/
	@Excel(name = "权限id", width = 15)
    @ApiModelProperty(value = "权限id")
	private String permissionId;
	/**数据规则id*/
	@ApiModelProperty(value = "数据规则id")
	private String dataRuleIds;

	public SysAgentPermission() {

	}

	public SysAgentPermission(String agentId, String permissionId) {
		this.agentId = agentId;
		this.permissionId = permissionId;
	}
}
