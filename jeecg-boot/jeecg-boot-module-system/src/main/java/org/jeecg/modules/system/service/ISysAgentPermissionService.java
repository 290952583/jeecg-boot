package org.jeecg.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.system.entity.*;

import java.util.List;

/**
 * @Description: 部门权限表
 * @Author: jeecg-boot
 * @Date:   2020-02-11
 * @Version: V1.0
 */
public interface ISysAgentPermissionService extends IService<SysAgentPermission> {
    /**
     * 保存授权 将上次的权限和这次作比较 差异处理提高效率
     * @param departId
     * @param permissionIds
     * @param lastPermissionIds
     */
    public void saveAgentPermission(String departId, String permissionIds, String lastPermissionIds);

    /**
     * 根据部门id，菜单id获取数据规则
     * @param permissionId
     * @return
     */
    List<SysPermissionDataRule> getPermRuleListByAgentIdAndPermId(String departId, String permissionId);
}
