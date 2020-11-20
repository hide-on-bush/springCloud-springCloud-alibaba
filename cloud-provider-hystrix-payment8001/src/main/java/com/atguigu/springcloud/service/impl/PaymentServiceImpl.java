/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.atguigu.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author:夏世雄
 * @Date: 2020/11/20 11:05
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
@Service
public class PaymentServiceImpl implements PaymentService {

   //=============================================服务降级start===========================================
    /**
     * 正常访问ok的方法
     * @param id
     * @return
     */
    @Override
    public String getPaymentInfoOk(Integer id){
        return "线程池：" + Thread.currentThread().getName() + "PaymentInfo_Ok: " + id + "\t" + "O(∩_∩)O哈哈~";
    }

    /**
     * @HystrixCommand启用服务降级
     * fallbackMethod 超时、出错或异常调用的方法名
     * @param id
     * @return
     */
    @Override
    @HystrixCommand(fallbackMethod = "getPaymentInfoTimeoutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "4000")
    })
    public String getPaymentInfoTimeout(Integer id) {
        int timeNum = 3;
        try{
            TimeUnit.SECONDS.sleep(timeNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "getPaymentInfo_Timeout: " + id
                + "\t" + "O(∩_∩)O哈哈~" + "耗时:" + timeNum + "秒";
    }


    /**
     * 用于服务降级 的回调函数
     * @return
     */
    public String getPaymentInfoTimeoutHandler(Integer id){
        return "线程池：" + Thread.currentThread().getName() + "getPaymentInfoTimeoutHandler: " + id
                + "\t" + "o(╥﹏╥)o，系统繁忙，请稍后再试！" ;
    }


    //=============================================服务降级end===========================================

    //=============================================服务熔断start=========================================
    //断路器开启或关闭的条件
    //①当满足一定的阈值的时候（默认10秒内超过20次请求）
    //②当失败率达到一定的时候（默认10秒内超过50%的请求失败）
    //③达到以上阈值，断路器将会开启

    //④当断路器开启的时候，所有的请求都不会进行转发，走fallback方法（服务降级）
    //⑤一段时间之后（默认为5s），这个时候断路器是半开状态，会让其中一个请求进行转发
    //⑥如果成功，则断路器会关闭，若失败继续开启。重复④和⑤

    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback",commandProperties = {
            //10秒钟内请求10次有60%的请求失败则跳闸
            @HystrixProperty(name="circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数，默认为10
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期 10s，默认值为20000
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60")//失败率达到多少后跳闸，默认值为50
    })
    @Override
    public String paymentCircuitBreaker(Integer id){
        if (id < 0){
            throw new RuntimeException("**************id不能为负数**********");
        }

        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号为：" + serialNumber;
    }

    public String paymentCircuitBreakerFallback(Integer id){
       return "id不能为负数，请稍后再试！" ;
    }
}
