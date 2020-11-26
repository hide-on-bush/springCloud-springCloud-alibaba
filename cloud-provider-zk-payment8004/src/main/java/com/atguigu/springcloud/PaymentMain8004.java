/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author:夏世雄
 * @Date: 2020/11/17 11:43
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: zookeeper做服务注册中心时，是用临时节点
 **/
@SpringBootApplication
//只要不是用eureka做服务注册中心，都可以用@EnableDiscoveryClient将服务注册至注册中心
@EnableDiscoveryClient
public class PaymentMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8004.class, args);
    }
}
