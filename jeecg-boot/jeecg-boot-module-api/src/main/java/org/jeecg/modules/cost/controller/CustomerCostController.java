package org.jeecg.modules.cost.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.cost.entity.CustomerCost;
import org.jeecg.modules.cost.service.CustomerCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author z
 */
@RestController
@RequestMapping("/customer/cost")
@Slf4j
@Api(tags = "用户的费用")
public class CustomerCostController {

    @Autowired
    private CustomerCostService customerCostService;

    @ApiOperation("抽取手续费用")
    @PutMapping("/extractCost")
    public Result<?> extractCost(String userId){
        try {
            customerCostService.extractCost(userId);
        }catch (Exception e){
            return Result.error(e.getMessage());
        }

        return Result.ok();
    }

}
