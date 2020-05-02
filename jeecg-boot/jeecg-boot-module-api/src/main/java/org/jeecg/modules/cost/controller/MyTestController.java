package org.jeecg.modules.cost.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author z
 */

@Api(tags = "测试模块")
@RestController
@RequestMapping("/my/test")
@Slf4j
public class MyTestController {


    @ApiOperation("第一个测试接口")
    @GetMapping("/t1")
    public String test(String msg){
        log.debug("请求t1接口");
        return msg;
    }

}
