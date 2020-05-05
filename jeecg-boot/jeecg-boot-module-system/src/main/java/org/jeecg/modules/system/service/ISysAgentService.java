package org.jeecg.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.common.system.vo.SysAgentModel;
import org.jeecg.modules.system.entity.SysAgent;
import org.jeecg.modules.system.model.SysAgentTreeModel;

import java.util.List;

/**
 * @Description: 代理商
 * @Author: jeecg-boot
 * @Date:   2020-04-24
 * @Version: V1.0
 */
public interface ISysAgentService extends IService<SysAgent> {

    /**
     * 通过用户id查询代理商树行结构信息（仅限系统管理员 和 代理商）
     * @param userId 用户id
     * @param keyword 搜索字
     * @return
     */
    List<SysAgentTreeModel> queryMyDeptTreeList(String userId, String keyword);

    /**
     * 添加代理商
     * @param sysAgentModel
     */
    void add(SysAgentModel sysAgentModel);

    /**
     * 同一个区域下是否存在相同的代理商名称
     * @param Id 代理商id
     * @param areaId 区域id
     * @param agentName 代理商名称
     * @return
     */
    boolean isExistAgentName(String Id, int areaId, String agentName);

    /**
     * 通过id获取代理商信息
     * @param id 代理商id
     * @return
     */
    SysAgentModel findById(String id);

}
