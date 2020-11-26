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
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * @Author:夏世雄
 * @Date: 2020/11/26 11:12
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: cap理论 c一致性 a可用性 p分区容错
 * nacos支持ap和cp模式的切换
 * 一般来说：如果不需要存储服务级别的信息且服务实例是通过nacos-client注册，并且能够保持心跳上报，那么就可以选择ap模式。当前
 *         主流的服务，如spring Cloud和dubbo服务，都适用于ap模式，ap模式为l服务的可用性而减弱了一致性，因此ap模式下只支持注册临时实例
 *
 *         如果需要在服务级别编辑或存储配置信息，那么cp是必须的，k8s和dns服务则适用于cp模式
 *         cp模式支持注册持久化实例，此时则以Raft协议为集群运行模式，该模式下注册实例之前必须先注册服务，如果服务不存在，则会报错。
 *
 *  默认为ap，如果想切换为cp 可用如下方式
 *  curl -X PUT '$NACOS_SERVER:8848/nacos/v1/ns/operator/switches?entry=serverMode&value=CP'
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OrderNocas9003 {
    public static void main(String[] args) {
        SpringApplication.run(OrderNocas9003.class,args);
    }
}
