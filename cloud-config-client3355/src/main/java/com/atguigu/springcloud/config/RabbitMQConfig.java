/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.atguigu.springcloud.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:夏世雄
 * @Date: 2020/11/24 17:17
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
@Configuration
public class RabbitMQConfig {
    //direct模式，直接根据队列名称投递消息
    @Bean
    public Queue logOpQueue(){
        return new Queue("oplog");
    }

    @Bean
    public Queue logErrQueue(){
        return new Queue("errlog");
    }

    //不要偷懒 一定要声明
    @Bean
    public Queue chatMessageQueue(){
        return new Queue("chatMessage");
    }

}
