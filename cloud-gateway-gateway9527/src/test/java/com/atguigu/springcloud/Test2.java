/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.atguigu.springcloud;

import java.time.ZonedDateTime;

/**
 * @Author:夏世雄
 * @Date: 2020/11/23 15:04
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
public class Test2 {
    public static void main(String[] args) {
        //默认时区的当前时间
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        //2020-11-23T15:06:21.640+08:00[Asia/Shanghai]
        System.out.println(zonedDateTime);
    }
}
