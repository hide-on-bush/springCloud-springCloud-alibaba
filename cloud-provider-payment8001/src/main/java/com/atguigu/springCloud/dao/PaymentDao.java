package com.atguigu.springCloud.dao;/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */

import com.atguigu.springCloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author:夏世雄
 * @Date: 2020/10/30 14:21
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
@Mapper
public interface PaymentDao {

    int create(Payment payment);
    Payment getPaymentById(@Param("id") Long id);
}
