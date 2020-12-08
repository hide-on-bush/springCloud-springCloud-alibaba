/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.domain.Order;
import com.atguigu.springcloud.mapper.OrderMapper;
import com.atguigu.springcloud.service.AccountService;
import com.atguigu.springcloud.service.OrderService;
import com.atguigu.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:夏世雄
 * @Date: 2020/12/4 13:13
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
@Service
@Slf4j
public class OrderServiceImpl  implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private StorageService storageService;

    @Autowired
    private AccountService accountService;

    /**
     * 下订单-扣减库存-扣减账号余额，修改订单状态
     * @param order
     * @return
     */
    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public boolean createOrder(Order order) {
        log.info("======================>order服务创建订单start，请求参数order：{}",order);
        boolean result = false;

        int createResult = orderMapper.createOrder(order);
        if (createResult == 1) {
            log.info("order==================>storage   调用库存微服务，扣减库存  start。");
            storageService.updateStorage(order.getProductId(),order.getCount());
            log.info("order==================>storage   调用库存微服务，扣减库存完成  end。");


            log.info("order==================> account  调用账号服务，扣减余额  start。");
            accountService.updateAccount(order.getUserId(),order.getMoney());
            log.info("order==================> account  调用账号服务，扣减余额完成 end 。");


            log.info("==================>order 订单服务，修改订单状态为已完成  start。");
            this.updateOrder(order.getUserId(),order.getStatus());
            log.info("==================>order 订单服务，修改订单状态为已完成  end。");


            result = true;
        }
        log.info("=================>下单完成，谢谢使用！======================");
        return result;
    }

    @Override
    public boolean updateOrder(Long userId, Integer status) {
        return orderMapper.updateOrder(userId,status) == 1;
    }
}
