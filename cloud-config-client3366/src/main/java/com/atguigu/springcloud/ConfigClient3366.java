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
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author:夏世雄
 * @Date: 2020/11/24 11:44
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 配置中心中配置修改之后只需要 配置中心发送post请求通知client端即可实现配置的动态修改
 * curl -X POST "http://localhost:3344/actuator/bus-refresh"
 *
 * 基本原理：configClient实例都会监听MQ中同一个topic（默认是springCloudBus）当一个服务刷新数据的时候，它会把信息放入到topic
 * 中，这样其他监听同一个topic的服务就能得到通知，然后去更新自身配置
 *
 * 2想实现定点通知该怎么做？即当config center对应的配置在gitHub上修改了 只想通知3355微服务去更新配置，而3366保持不变。
 * 公式：http://localhost:配置中心端口号/actuator/bus-refresh/{destination}
 * destination参数类型指定需要更新配置的服务或实例（可理解为微服务名称:端口号）
 * 如下使用就能精确通知到3355，而3366则不会被通知
 * curl -X POST "http://localhost:3344/actuator/bus-refresh/config-client:3355"
 *
 **/
@SpringBootApplication
@EnableEurekaClient
public class ConfigClient3366 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClient3366.class,args);
    }
}
