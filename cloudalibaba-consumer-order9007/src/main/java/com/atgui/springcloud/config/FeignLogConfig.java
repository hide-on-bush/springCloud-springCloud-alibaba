/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.atgui.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:夏世雄
 * @Date: 2020/12/3 15:30
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
@Configuration
public class FeignLogConfig {

    @Bean
    Logger.Level feignLoggerLevel(){
        //feign日志级别 none 默认的，不显示任何日志
        //basic 仅记录请求方法、url、响应状态码及执行时间
        //headers 除了basic中定义的信息之外，还有请求和响应头信息
        //full 除了headers中定义的信息之外，还有请求和响应的正文及元数据
        return Logger.Level.FULL;
    }
}
