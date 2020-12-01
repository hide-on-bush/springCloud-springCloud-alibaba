/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author:夏世雄
 * @Date: 2020/12/1 14:20
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
@RestController
@Slf4j
public class FlowLimitController {

    /**
     * qps限流：每秒钟的请求数    御敌于国门之外
     * 线程数：当调用该qpi的线程数超过设定的阈值时进行限流，关门打狗的思想
     * @return
     */
    @GetMapping("/test1")
    public String test1(){
        return "***************** test sentinel流量控制method 1*******************";
    }

    @GetMapping("/test2")
    public String test2(){
        log.info(Thread.currentThread().getName()+"\t" + ".......  test2");
        return "***************** test sentinel流量控制method 2*******************";
    }

    /**
     * 服务降级： 平均响应时间
     * @return
     */
    @GetMapping("/test/rt")
    public String testRt(){
//        try{
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        log.info("=============test rt服务降级策略--->平均响应时间===========");
        //异常比例
        log.info("=============test 服务降级策略--》异常比例===========");
        int age = 10/0;
        return "平均响应时间超出阈值且在时间窗口内通过的请求>=5，两个条件都满足后触发降级，窗口期过后关闭断路器。";
    }

    @GetMapping("/test/exceptionNum")
    public String testExceptionNum(){
        log.info("=============test 服务降级策略--》异常数===========");
        int age = 10/0;
        return "============测试服务降级策略，异常数===========";
    }
}
