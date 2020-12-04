/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.mapper.PaymentMapper;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:夏世雄
 * @Date: 2020/12/3 14:58
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public CommonResult<Payment> getPaymentById(Long id) {
        Payment payment = paymentMapper.getPaymentById(id);
        CommonResult<Payment> result = new CommonResult<>(200,"根据id获取payment success",payment);
        return result;
    }

    @Override
    public CommonResult createPayment(Payment payment) {
        CommonResult commonResult;
        int value = paymentMapper.createPayment(payment);
        if (value == 1) {
            commonResult =  new CommonResult(200,"新增payment success");
        } else {
            commonResult = new CommonResult(400,"新增payment success fail");
        }
        return commonResult;
    }
}
