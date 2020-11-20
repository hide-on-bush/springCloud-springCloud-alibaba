/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
}
