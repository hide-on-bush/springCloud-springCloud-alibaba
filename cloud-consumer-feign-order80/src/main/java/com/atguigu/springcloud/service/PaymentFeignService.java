package com.atguigu.springcloud.service;/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @Author:夏世雄
 * @Date: 2020/11/18 17:05
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 该接口中的方法与被调用方controller中的方法保持一致，路径也要一致
 **/
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE") //value找那个微服务
public interface PaymentFeignService {

    @PostMapping(value = "/payment/create")
    CommonResult create(@RequestBody Payment payment);


    @GetMapping(value ="/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/payment/feign/timeout")
    String paymentFeignTimeout();
}
