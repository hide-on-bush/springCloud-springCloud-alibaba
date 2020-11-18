/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:夏世雄
 * @Date: 2020/11/18 14:25
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 自定义ribbon服务调用规则
 * 负载均衡算法：
 *  ①轮询 rest接口第几次请求数 % 服务器集群总数量 = 实际调用服务器位置下标，每次服务重启后，rest接口计数从一开始
 *  List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");根据服务名获取服务实例
 *  如:List[0] instance = 127.0.0.1:8001; List[0] instance = 127.0.0.1:8002;
 *  第一次请求  1 % 2 = 1 ，则调用List下标为1的服务实例  127.0.0.1:8002
 *  第二次请求  2 % 2 = 0 ，则调用List下标为0的服务实例  127.0.0.1:8001
 *  第三次请求  3 % 2 = 1 ，则调用List下标为1的服务实例  127.0.0.1:8002
 *  第四次请求  4 % 2 = 0 ，则调用List下标为0的服务实例  127.0.0.1:8001
 *  以此类推，当服务器重启，又从第一次请求开始计数。
 *
 **/
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){
        //定义为随机
        return new RandomRule();
    }
}
