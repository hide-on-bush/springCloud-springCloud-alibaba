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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author:夏世雄
 * @Date: 2020/10/30 16:29
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 先使用 restTemplate（底层封装了HttpClient）调用payment服务
 **/
@Slf4j
@RestController
public class OrderController {

   @Autowired
   private RestTemplate restTemplate;

   //private static final String PAYMENT_URL = "http://localhost:8001";

   private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

   @GetMapping("/consumer/payment/create")
   public CommonResult<Payment> create(Payment payment) {
       log.info("请求参数payment为：" + payment);
       return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
   }

   @GetMapping("/consumer/payment/get/{id}")
   public CommonResult getPayment(@PathVariable Long id){
       log.info("请求参数id为：" + id);
       return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id ,CommonResult.class);
   }
}
