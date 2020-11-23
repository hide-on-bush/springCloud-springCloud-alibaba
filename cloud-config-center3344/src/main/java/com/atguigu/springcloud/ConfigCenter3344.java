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
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Author:夏世雄
 * @Date: 2020/11/23 17:24
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 * 配置读取规则
 * ① /label/application-profile.yml  /master/config-dev.yml
 * ② /application-profile.yml        /config-dev.yml
 * ③  /application/profile/label     /config/dev/master
 *
 * label 分支  application 服务名  profile 环境（dev/test/prod）
 *
 *
 **/
@SpringBootApplication
@EnableConfigServer // 开启spring cloud config
public class ConfigCenter3344 {

    public static void main(String[] args) {
        SpringApplication.run(ConfigCenter3344.class, args);
    }
}
