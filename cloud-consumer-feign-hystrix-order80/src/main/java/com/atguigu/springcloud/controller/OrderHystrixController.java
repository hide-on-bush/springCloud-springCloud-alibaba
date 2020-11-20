/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author:夏世雄
 * @Date: 2020/11/20 13:09
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
@RestController
@Slf4j
//@DefaultProperties(defaultFallback = "globalFallBackHandler") //全局fallback
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/hystrix/ok/{id}")
    public String getPaymentInfoOk(@PathVariable("id") Integer id){
        return paymentHystrixService.getPaymentInfoOk(id);
    }

//    @HystrixCommand(fallbackMethod = "fallBack" ,commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    @GetMapping("/consumer/hystrix/timeOut/{id}")
    public String getPaymentInfoTimeout(@PathVariable("id") Integer id){
        return paymentHystrixService.getPaymentInfoTimeout(id);
    }

    /**
     * 坑：回调函数的参数要与被回调的方法的参数保持一致。不然报错，消费方（客户端）做服务降级时，在controller中写回调函数即可
     * @param id
     * @return 这个是定制的fallback方法
     */
    public String fallBack( Integer id){
        return "=============嗨，亲爱的消费者，系统繁忙，请稍后！定制的fallback============";
    }


    /**
     * 想使用全局fallback的方法，必须在方法上加上@HystrixCommand注解
     * @return
     */
    public String globalFallBackHandler(){
        return "**************全局异常处理信息，系统繁忙，请稍后！**************";
    }
}
