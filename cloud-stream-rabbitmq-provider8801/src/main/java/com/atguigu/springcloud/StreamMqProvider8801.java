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
 * @Date: 2020/11/25 11:07
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 消息生产者8801
 **/
@EnableEurekaClient
@SpringBootApplication
public class StreamMqProvider8801 {
    public static void main(String[] args) {
        SpringApplication.run(StreamMqProvider8801.class,args);
    }
}
