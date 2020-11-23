/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.atguigu.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @Author:夏世雄
 * @Date: 2020/11/23 16:21
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 路由过滤器 可用于修改进入的http请求和返回的http响应，路由过滤器只能指定路由进行使用
 * 生命周期 pre 业务逻辑之前 post业务逻辑之后
 * 种类 GatewayFilter（单一的） GlobalFilter（全局的）
 * 常用的过滤器
 *
 * 自定义全局过滤器： implements GlobalFilter,Ordered  重写filter方法
 * 能干嘛：全局日志记录；统一网关鉴权..........
 **/
@Component
@Slf4j
public class MyGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("************* coming myGlobalFilter:" +new Date());
        String userName = exchange.getRequest().getQueryParams().getFirst("username");
        if (StringUtils.isEmpty(userName)) {
            log.info("************* 用户名为null，非法用户************");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
