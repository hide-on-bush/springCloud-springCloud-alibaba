/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:夏世雄
 * @Date: 2020/11/25 14:39
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 注意在springCloud stream中处于同一个group中的多个消费者是竞争关系，就能够保证消息只会被其中一个应用消费一次，
 * 避免重复消费，不同组是可以重复消费的。
 **/
@EnableBinding(Sink.class)
@Slf4j
public class ConsumerMessageController {

    @Value("${server.port}")
    private String serverPort;

    /**
     * 服务消费方法在controller中部需要 mapping以及方法只能为void
     * @param message
     */
    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
        log.info("消费者2，serverPort为{},message为{}",serverPort,message.getPayload());
    }
}
