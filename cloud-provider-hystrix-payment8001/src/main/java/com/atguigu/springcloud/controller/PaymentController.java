/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:夏世雄
 * @Date: 2020/11/20 11:18
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
@RestController
@Slf4j
public class PaymentController {

    //========================================服务降级start==================================

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String getPaymentInfoOk(@PathVariable("id") Integer id){
        String result = paymentService.getPaymentInfoOk(id);
        log.info("*************result:" + result);
        return result;
    }

    @GetMapping("/payment/hystrix/timeOut/{id}")
    public String getPaymentInfoTimeout(@PathVariable("id") Integer id){
        String result = paymentService.getPaymentInfoTimeout(id);
        log.info("*************result:" + result);
        return result;
    }

    //========================================服务降级end====================================

    //========================================服务熔断start==================================
    //服务熔断（保险丝）   服务降级-》服务熔断-》恢复调用链路

    @GetMapping("/payment/hystrix/cricuitBreaker/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("**************result :" + result);
        return result;
    }
}
