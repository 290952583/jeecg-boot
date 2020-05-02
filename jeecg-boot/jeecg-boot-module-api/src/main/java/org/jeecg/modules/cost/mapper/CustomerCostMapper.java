package org.jeecg.modules.cost.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.cost.entity.CustomerCost;


/**
 * @author z
 */
public interface CustomerCostMapper extends BaseMapper<CustomerCost> {

    /**
     * 根据用户id取用户的余额
     * @param id 用户id
     * @return 余额信息
     */
    CustomerCost selectByUserId(String id);
}
