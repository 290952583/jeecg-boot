package org.jeecg.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.vo.SysAgentModel;
import org.jeecg.modules.system.entity.SysAgent;
import org.jeecg.modules.system.entity.SysDepart;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.mapper.SysAgentMapper;
import org.jeecg.modules.system.mapper.SysUserAgentMapper;
import org.jeecg.modules.system.mapper.SysUserMapper;
import org.jeecg.modules.system.model.SysAgentTreeModel;
import org.jeecg.modules.system.model.SysDepartTreeModel;
import org.jeecg.modules.system.service.ISysAgentService;
import org.jeecg.modules.system.util.FindsAgentsChildrenUtil;
import org.jeecg.modules.system.util.FindsDepartsChildrenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 代理商
 * @Author: jeecg-boot
 * @Date:   2020-04-24
 * @Version: V1.0
 */
@Service
public class SysAgentServiceImpl extends ServiceImpl<SysAgentMapper, SysAgent> implements ISysAgentService {

    @Resource
    private SysAgentMapper sysAgentMapper;
    @Resource
    private SysUserAgentMapper sysUserAgentMapper;
    @Resource
    private SysUserMapper sysUserMapper;


    @Override
    public List<SysAgentTreeModel> queryMyDeptTreeList(String userId, String keyword) {
        //根据部门id获取所负责部门
        SysUser sysUser = sysUserMapper.selectById(userId);
        if (sysUser == null) throw new NullPointerException("用户不存在");
        List<String> agentIds = sysUserAgentMapper.queryAgentIds(userId);
        List<SysAgentModel> sysAgentList = sysAgentMapper.queryAgentList(agentIds, keyword);
        // 调用wrapTreeDataToTreeList方法生成树状数据
        List<SysAgentTreeModel> listResult = FindsAgentsChildrenUtil.wrapTreeDataToTreeList(sysAgentList);

        return listResult;
    }


    @Override
    @Transactional
    public void add(SysAgentModel sysAgentModel) {
        SysAgent sysAgent = new SysAgent();
        boolean existAgentName = isExistAgentName(null, sysAgentModel.getAreaId(), sysAgentModel.getAgentName());
        if (existAgentName) throw  new JeecgBootException("代理商名称已存在");
        BeanUtils.copyProperties(sysAgentModel, sysAgent);
        sysAgent.setCreateBy(sysAgentModel.getUserName());
        save(sysAgent);
    }

    @Override
    public SysAgentModel findById(String id) {
        sysAgentMapper.findById(id);
        return null;
    }
    @Override
    public boolean isExistAgentName(String Id, int areaId, String agentName){
        SysAgentModel sysAgentModel = sysAgentMapper.getByAreaIdAndAgentName(areaId, agentName);
        if (sysAgentModel != null && StringUtils.isNotBlank(sysAgentModel.getId()) && !sysAgentModel.getId().equals(Id)){
            return true;
        }
        return false;

    }


}
