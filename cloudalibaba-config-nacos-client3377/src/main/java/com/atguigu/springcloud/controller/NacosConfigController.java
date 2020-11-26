/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:夏世雄
 * @Date: 2020/11/26 14:47
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: nacos同springCloud config一样，在项目初始化时，要先保证先从配置中心进行配置拉取。拉取配置之后，
 * 才能保证项目正常启动。所以nacos作为服务注册中心时也要有bootstrap.yml
 * springboot 中配置文件的加载是存在优先级顺序的，bootstrap优先级高于application
 *
 **/
@RestController
@RefreshScope//实现配置自动更新，保证自动刷新
public class NacosConfigController {


    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config/info")
    public String getConfigInfo() {
        return configInfo;
    }
}
