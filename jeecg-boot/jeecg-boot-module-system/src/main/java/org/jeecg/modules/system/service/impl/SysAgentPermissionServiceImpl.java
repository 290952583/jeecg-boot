package org.jeecg.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.system.entity.SysAgentPermission;
import org.jeecg.modules.system.entity.SysDepartPermission;
import org.jeecg.modules.system.entity.SysPermission;
import org.jeecg.modules.system.entity.SysPermissionDataRule;
import org.jeecg.modules.system.mapper.SysAgentPermissionMapper;
import org.jeecg.modules.system.mapper.SysDepartPermissionMapper;
import org.jeecg.modules.system.mapper.SysPermissionDataRuleMapper;
import org.jeecg.modules.system.mapper.SysPermissionMapper;
import org.jeecg.modules.system.service.ISysAgentPermissionService;
import org.jeecg.modules.system.service.ISysDepartPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Description: 部门权限表
 * @Author: jeecg-boot
 * @Date:   2020-02-11
 * @Version: V1.0
 */
@Service
public class SysAgentPermissionServiceImpl extends ServiceImpl<SysAgentPermissionMapper, SysAgentPermission> implements ISysAgentPermissionService {
    @Resource
    private SysPermissionDataRuleMapper ruleMapper;

    @Override
    public void saveAgentPermission(String agentId, String permissionIds, String lastPermissionIds) {
        List<String> add = getDiff(lastPermissionIds,permissionIds);
        if(add!=null && add.size()>0) {
            List<SysAgentPermission> list = new ArrayList<SysAgentPermission>();
            for (String p : add) {
                if(oConvertUtils.isNotEmpty(p)) {
                    SysAgentPermission rolepms = new SysAgentPermission(agentId, p);
                    list.add(rolepms);
                }
            }
            this.saveBatch(list);
        }
        List<String> delete = getDiff(permissionIds,lastPermissionIds);
        if(delete!=null && delete.size()>0) {
            for (String permissionId : delete) {
                this.remove(new QueryWrapper<SysAgentPermission>().lambda().eq(SysAgentPermission::getAgentId, agentId).eq(SysAgentPermission::getPermissionId, permissionId));
            }
        }
    }

    @Override
    public List<SysPermissionDataRule> getPermRuleListByAgentIdAndPermId(String agentId, String permissionId) {
        SysAgentPermission agentPermission = this.getOne(new QueryWrapper<SysAgentPermission>().lambda().eq(SysAgentPermission::getAgentId, agentId).eq(SysAgentPermission::getPermissionId, permissionId));
        if(agentPermission != null){
            LambdaQueryWrapper<SysPermissionDataRule> query = new LambdaQueryWrapper<SysPermissionDataRule>();
            query.in(SysPermissionDataRule::getId, Arrays.asList(agentPermission.getDataRuleIds().split(",")));
            query.orderByDesc(SysPermissionDataRule::getCreateTime);
            List<SysPermissionDataRule> permRuleList = this.ruleMapper.selectList(query);
            return permRuleList;
        }else{
            return null;
        }
    }

    /**
     * 从diff中找出main中没有的元素
     * @param main
     * @param diff
     * @return
     */
    private List<String> getDiff(String main,String diff){
        if(oConvertUtils.isEmpty(diff)) {
            return null;
        }
        if(oConvertUtils.isEmpty(main)) {
            return Arrays.asList(diff.split(","));
        }

        String[] mainArr = main.split(",");
        String[] diffArr = diff.split(",");
        Map<String, Integer> map = new HashMap<>();
        for (String string : mainArr) {
            map.put(string, 1);
        }
        List<String> res = new ArrayList<String>();
        for (String key : diffArr) {
            if(oConvertUtils.isNotEmpty(key) && !map.containsKey(key)) {
                res.add(key);
            }
        }
        return res;
    }
}
