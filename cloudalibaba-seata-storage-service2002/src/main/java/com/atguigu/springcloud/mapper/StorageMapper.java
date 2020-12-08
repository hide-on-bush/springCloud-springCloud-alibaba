/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.atguigu.springcloud.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author:夏世雄
 * @Date: 2020/12/4 14:23
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
@Mapper
public interface StorageMapper {

    /**
     * 扣减库存
     * @param productId 产品id
     * @param productNum 订单中对应产品的购买数量
     * @return
     */
    int updateStorage(@Param("productId") Long productId, @Param("productNum") Integer productNum);

}

