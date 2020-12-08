package com.atguigu.springcloud.mapper;/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */

import com.atguigu.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author:夏世雄
 * @Date: 2020/12/4 13:12
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
@Mapper
public interface OrderMapper {

    /**
     * 创建订单
     * @param order
     * @return
     */
    int createOrder(Order order);

    /**
     * 修改订单状态,从0到1
     * @param userId 用户id
     * @param status 订单状态
     * @return
     */
    int updateOrder(@Param("userId")Long  userId,@Param("status") Integer status);
}
