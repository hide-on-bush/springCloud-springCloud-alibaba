/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.mapper.AccountMapper;
import com.atguigu.springcloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @Author:夏世雄
 * @Date: 2020/12/4 15:19
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    /**
     * 根于用户id 更新用户账号余额
     *
     * @param userId
     * @param money  订单金额
     * @return
     */
    @Override
    public boolean updateAccount(Long userId, BigDecimal money) {
//        try{
//            TimeUnit.SECONDS.sleep(30);
//        }catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        log.info("====================扣减库存start***************************");
        return accountMapper.updateAccount(userId,money) == 1;

    }
}
