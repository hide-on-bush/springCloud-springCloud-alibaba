/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.MessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;


/**
 * @Author:夏世雄
 * @Date: 2020/11/25 11:34
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 此实现；类不需要使用@service注解
 **/
@EnableBinding(Source.class) //定义消息的推送管道（指信道channel和exchange绑定在一起）
@Slf4j
public class MessageProviderImpl implements MessageProvider {

    /**
     *  消息发送管道
     */
    @Autowired
    private MessageChannel output;

    @Override
    public String send() {
        String  message = UUID.randomUUID().toString();
        //构建消息
        output.send(MessageBuilder.withPayload(message).build());
        log.info("*****************发送消息message：" + message);
        return message;
    }
}
