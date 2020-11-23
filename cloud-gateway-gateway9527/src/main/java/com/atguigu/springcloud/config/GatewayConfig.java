/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.atguigu.springcloud.config;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:夏世雄
 * @Date: 2020/11/23 14:14
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: gateway 统一管理路由的实现方式有两种 一种是yml配置，一种是java配置
 * 默认情况下gateway会根据注册中心注册的服务列表，以注册中心上服务名称为路径创建动态路由进行转发，从而实现动态路由功能
 **/
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        //一下路由是想通过9527访问百度新闻 https://news.baidu.com/guonei
        //访问http://localhost:9527/guonei将会转发到https://news.baidu.com/guonei
        routes.route("path_route_atguigu", r -> r.path("/guonei")
                .uri("https://news.baidu.com/guonei")).build();
        return routes.build();
    }

    @Bean
    public RouteLocator customerTransaction(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        //一下路由是想通过9527访问百度新闻 https://news.baidu.com/guoji
        //访问http://localhost:9527/guoji将会转发到https://news.baidu.com/guonei
        routes.route("transaction", r -> r.path("/guoji")
                .uri("https://news.baidu.com/guoji")).build();
        return routes.build();
    }
}
