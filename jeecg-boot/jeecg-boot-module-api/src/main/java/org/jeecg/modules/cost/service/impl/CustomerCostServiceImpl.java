package org.jeecg.modules.cost.service.impl;

import org.checkerframework.checker.units.qual.A;
import org.jeecg.common.system.base.service.impl.JeecgServiceImpl;
import org.jeecg.modules.cost.entity.CustomerCost;
import org.jeecg.modules.cost.mapper.CustomerCostMapper;
import org.jeecg.modules.cost.service.CustomerCostService;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.entity.SysUserAgent;
import org.jeecg.modules.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * @author z
 */
@Service
public class CustomerCostServiceImpl extends JeecgServiceImpl<CustomerCostMapper, CustomerCost> implements CustomerCostService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private CustomerCostMapper customerCostMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void extractCost(String userId) {
        //获取商家信息和代理商信息
        SysUser user = sysUserMapper.selectById(userId);
        CustomerCost customerCost = customerCostMapper.selectByUserId(user.getId());

        List<Object> agent = new ArrayList<>();

        //验证商家身份
        if(user.getIdentity() == SysUser.Identity.BUSINESS.getIdentity()){
            throw new IllegalArgumentException("身份错误");
        }

        //验证商家状态
        if(user.getStatus() == SysUser.Status.FROZEN.getStatus()){
            throw new IllegalArgumentException("账户已冻结");
        }

        //验证商家的余额
        if(customerCost.getCurrentCost().doubleValue() <= 0){
            throw new IllegalArgumentException("余额不足，请充值");
        }

        //扣费



        //代理商分成

    }
}
