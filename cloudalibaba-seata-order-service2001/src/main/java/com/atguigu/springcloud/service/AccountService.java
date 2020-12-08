package com.atguigu.springcloud.service;/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @Author:夏世雄
 * @Date: 2020/12/4 13:56
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 账户service
 **/
@FeignClient(value = "seata-account-service")
public interface AccountService {

    /**
     * 更新余额
     * @param userId
     * @param money
     * @return
     */
    @GetMapping("/account/pay")
    boolean updateAccount(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);



}
