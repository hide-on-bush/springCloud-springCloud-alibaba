/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.atguigu.springcloud.loadblancer;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:夏世雄
 * @Date: 2020/11/18 15:24
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 自定义负载均衡算法
 **/
@Component
public class MyLoadBalancer implements LoadBalancer{

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * cas 加自旋锁
     * @return
     */
    public final int getAndIncrement(){
        int current;
        int next;
        do{
            current = atomicInteger.get();
            //Integer.MAX_VALUE = 2147483647
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("******************第几次访问，next:" + next);
        return next;
    }

    /**
     * 获取服务实例
     *
     * @param serviceInstances
     * @return 负载均衡算法： rest接口第几次访问 % 服务器集群总数量 = 实际调用服务器位置下标，每次重启rest接口计数从一开始
     */
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
