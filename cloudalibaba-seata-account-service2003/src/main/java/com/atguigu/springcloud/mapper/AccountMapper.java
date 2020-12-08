package com.atguigu.springcloud.mapper;/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @Author:夏世雄
 * @Date: 2020/12/4 15:20
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
@Mapper
public interface AccountMapper {

    /**
     * 根于用户id 更新用户账号余额
     * @param userId
     * @param money 订单金额
     * @return
     */
    int updateAccount(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
