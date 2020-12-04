/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.atguigu.springcloud.myHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;


/**
 * @Author:夏世雄
 * @Date: 2020/12/3 10:58
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 全局异常处理
 **/
public class CustomerBlockHandler {

    public static CommonResult handleException1(BlockException exception){
        return new CommonResult(444,"按用户自定义 global handlerException --- 1");
    }

    public static CommonResult handleException2(BlockException exception){
        return new CommonResult(444,"按用户自定义 global handlerException --- 2");
    }
}
