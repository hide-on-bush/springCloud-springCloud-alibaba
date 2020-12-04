/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.atgui.springcloud.service.impl;

import com.atgui.springcloud.service.OrderService;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @Author:夏世雄
 * @Date: 2020/12/3 16:36
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
@Component
public class OrderServiceImpl implements OrderService {

    @Override
    public CommonResult<Payment> getPaymentById(Long id) {
        return new CommonResult<>(998,"服务降级！");
    }

    @Override
    public CommonResult createPayment(Payment payment) {
        return new CommonResult<>(996,"服务降级！");
    }
}
