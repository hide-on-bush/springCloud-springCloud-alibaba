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
 * @Date: 2020/11/24 9:23
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 * config的动态刷新问题 ：
 *  问题描述：将配置中心3344中对应的config-dev.yml文件 中的config info配置中的version改成2之后，
 *  http://config-3344.com:3344/main/config-dev.yml 能立马得到改变后的信息，而配置中心client端则不能得到新的配置
 *  使用 http://localhost:3355/getConfigInfo 访问version仍然是1，重启3355微服务后才能获取新配置。
 *
 * 2.解决方案： 手动动态刷新
 *   ①在pom文件中加入 spring-boot-starter-actuator 依赖
 *   ②在bootstrap.yml（config client端配置文件） 中添加暴露监控端点配置项
 *   ③在业务类及controller上添加@RefreshScope 注解
 *   ④需要运维人员发送post请求刷新3355(即config client)  curl -X POST "http://localhost:3355/actuator/refresh"
 *   ⑤成功实现了3355客户端刷新到最新配置内容，避免了服务重启
 *
 * 3.当微服务过多时，上述方法就有点力不从心了。可否广播、一次通知处处生效，或精确打击？ 可以，使用消息总线 spring cloud bus
 **/
@SpringBootApplication
@EnableEurekaClient
public class ConfigClient3355 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClient3355.class,args);
    }
}
