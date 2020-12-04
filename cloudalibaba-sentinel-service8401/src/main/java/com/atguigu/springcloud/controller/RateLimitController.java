/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.myHandler.CustomerBlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:夏世雄
 * @Date: 2020/12/2 15:47
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
@RestController
@Slf4j
public class RateLimitController {

    /**
     * 此方法访问超过我们配置的限流规则，则会走我们自定义的blockHandler
     * @return
     */
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException",fallback = "fallback")
    public CommonResult byResource(){
        int age = 10 /0;
        return new CommonResult(200,"按资源名限流ok",new Payment(16L,"serial001"));
    }

    public CommonResult handleException(BlockException exception){
        return new CommonResult(400,exception.getClass().getCanonicalName() +" \t 服务不可用");
    }

    /**
     * 此方法超过我们配置的限流规则，走sentinel系统默认的blockHandler
     * @return
     */
    @GetMapping("/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl(){
        return new CommonResult(200,"按url限流ok",new Payment(17L,"serial002"));
    }

    /**
     * fallback方法与调用的方法，返回值和参数必须一致，否则不可用
     * @return
     */
    public CommonResult fallback(){
        return new CommonResult(400," 服务器异常");
    }


    //===========================================使用自定义CustomerBlockerHandler来进行全局异常处理=======================================

    @GetMapping("/useCustomer")
    @SentinelResource(value = "useCustomer",blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handleException2")
    public CommonResult useCustomer(){
        return new CommonResult(200,"使用自定义customerBlockerHandler成功",new Payment(18L,"serial003"));
    }

}
