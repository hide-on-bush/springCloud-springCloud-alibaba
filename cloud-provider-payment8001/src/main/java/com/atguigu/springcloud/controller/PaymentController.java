/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author:夏世雄
 * @Date: 2020/10/30 14:46
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/

@Slf4j
@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * 服务发现client端 通过服务发现可以获得该服务的信息（对于注册进eureka服务中心的服务）
     */
    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * 从配置文件读取端口号
     */
    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int count = paymentService.create(payment);
        log.info("*************插入结果：" +  count);
        if (count > 0) {
            return new CommonResult(200,"添加订单成功，serverPort：" + serverPort, count);
        } else {
            return new CommonResult(400,"添加订单失败");
        }
    }

    @GetMapping(value ="/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("*************查询结果：" +  payment);
        if (payment != null) {
            return new CommonResult(200,"查询成功，serverPort：" + serverPort,payment);
        } else {
            return new CommonResult(400,"查询失败，查询id：" + id);
        }
    }

    @GetMapping("/payment/discovery")
    public Object getDiscovery() {
        //获取Eureka server中所有的服务名称
        List<String>  services =  discoveryClient.getServices();
        for (String service : services) {
            log.info("********************service:" + service);
        }
        //根据服务名称获取所有的服务实例
        List<ServiceInstance> instances =  discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" +instance.getPort() + "\t" +instance.getUri());
        }

        return this.discoveryClient;
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try{
            //线程暂停几秒钟
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }


    @GetMapping("/payment/zipkin")
    public String paymentZipkin(){
        return  "hi, i am paymentZipkin server fall back ,welcome to use me! port is " + serverPort;
    }
}
