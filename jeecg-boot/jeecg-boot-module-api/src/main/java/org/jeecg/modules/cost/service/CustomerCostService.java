package org.jeecg.modules.cost.service;

import org.jeecg.common.system.base.service.JeecgService;
import org.jeecg.modules.cost.entity.CustomerCost;

/**
 * @author : z
 */
public interface CustomerCostService extends JeecgService<CustomerCost> {

    /**
     *  抽取商家的服务费
     * @param userId 商家id
     */
    void extractCost(String userId);
}
