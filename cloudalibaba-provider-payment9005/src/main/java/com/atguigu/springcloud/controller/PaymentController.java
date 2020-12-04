/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:夏世雄
 * @Date: 2020/12/3 14:28
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private PaymentService paymentService;


    /**
     * fallback 管运行时异常、blockHandler管配置违规
     * @param id  blockHandler和fallback都配置，则被限流降级而抛出 BlockException时只会进入blockHandler处理逻辑
     *            exceptionsToIgnore = {IllegalArgumentException.class} 当程序报该异常时，sentinel不进行任何处理
     * @return
     */
    //@SentinelResource(value = "getPaymentById", fallback = "fallback") //fallback只负责业务异常
    //@SentinelResource(value = "getPaymentById", blockHandler = "blockHandler") //blockHandler只负责sentinel控制台配置违规
    //@SentinelResource(value = "getPaymentById", blockHandler = "blockHandler",fallback = "fallback"//,exceptionsToIgnore = {IllegalArgumentException.class}
    //)
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable(value = "id") Long id){
        if (id == 100) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常");
        }
        CommonResult<Payment> result = paymentService.getPaymentById(id);
        if (result.getData() == null) {
            throw  new NullPointerException("NullPointerException,没有该id对应的记录，空指针异常");
        }
        result.setMessage(result.getMessage() + " serverPort :" + serverPort);
        return result;
    }

    @PostMapping("/add/payment")
    public CommonResult  createPayment(@RequestBody Payment payment){
        CommonResult<Payment> result = paymentService.createPayment(payment);
        result.setMessage(result.getMessage() + " serverPort :" + serverPort);
        return result;
    }

    public CommonResult<Payment> fallback(@PathVariable(value = "id") Long id){
        return new CommonResult<>(404,"程序运行异常，处理方法，请稍后再试！");
    }

    /**
     *
     * @param id
     * @param exception 该参数必须要有，不然不生效
     * @return
     */
    public CommonResult<Payment> blockHandler(@PathVariable(value = "id") Long id, BlockException exception){
        return new CommonResult<>(998,"sentinel限流，查无此项，请重新输入！");
    }
}
