/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Service;

/**
 * @Author:夏世雄
 * @Date: 2020/11/20 15:40
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 该接口用于对PaymentHystrixService进行统一的服务降级
 **/
@Service
public class PaymentFallBackServiceImpl implements PaymentHystrixService {

    @Override
    public String getPaymentInfoOk(Integer id) {
        return "PaymentFallBackService  fallback,系统异常，请稍后再试！";
    }

    @Override
    public String getPaymentInfoTimeout(Integer id) {
        return "PaymentFallBackService  fallback,系统超时，请稍后再试！";
    }
}
