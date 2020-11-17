/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author:夏世雄
 * @Date: 2020/11/17 15:44
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 使用RestTemplate调用时在配置RestTemplate时一定要加上 @LoadBalanced 注解才能正常调用，不然报错
 **/
@RestController
@Slf4j
public class OrderZkController {

    public static final String INVOKE_URL = "http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/payment/zookeeper")
    public String paymentInfo(){
        String result = restTemplate.getForObject(INVOKE_URL + "/payment/zookeeper",String.class);
        return result;
    }
}
