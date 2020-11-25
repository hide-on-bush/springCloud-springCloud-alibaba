/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.loadblancer.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;
import java.util.Objects;


/**
 * @Author:夏世雄
 * @Date: 2020/10/30 16:29
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription: 先使用 restTemplate（底层封装了HttpClient）调用payment服务
 * 浅谈eureka、zookeeper、consul三种服务注册中心 eureka是ap（高可用和分区容错）；zookeeper和consul是cp（一致性和分区容错）
 * Ribbon客户端负载均衡（进程内负载均衡）即本地负载均衡，在调用微服务接口时，会在注册中心上获取服务注册表缓存到jvm本地，从而实现远程调用
 * nginx是服务器端负载均衡（集中式负载均衡），客户端所有请求都会交给nginx，然后由nginx实现转发请求。
 * ribbon其实就是负载均衡+RestTemplate
 * ribbon工作时会分为两步，第一步先选择eurekaServer ，它优先选择在同一个区域内负载较少的server
 * 第二步再根据用户的指定策略，再从server上取到的服务注册表中选择一个地址
 *
 *
 *
 * Ribbon常用的调用侧略：默认为轮询
 * RoundRobinRule  轮询
 * RandomRule 随机
 * RetryRule 先按照RoundRobinRule获取服务，如果获取失败了，则在指定的时间内会进行重试，获取可用服务
 * WeightResponseTimeRule 对RoundRobinRule的扩展，响应速度越快的实例选择权重越大，越容易被选择
 * BestAvailableRule 会过滤掉由于多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务
 * AvailabilityFilteringRule 先过滤掉故障实例，再选择并发较小的实例
 * ZoneAvoidanceRule 默认规则，复合判断server所在区域的性能和server的可用性选择服务
 **/
@Slf4j
@RestController
public class OrderController {

   @Autowired
   private RestTemplate restTemplate;

   //private static final String PAYMENT_URL = "http://localhost:8001";

   private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

   @Autowired
   private LoadBalancer loadBalancer;

    /**
     * 服务发现client端 通过服务发现可以获得该服务的信息（对于注册进eureka服务中心的服务）
     */
    @Resource
    private DiscoveryClient discoveryClient;

   @GetMapping("/consumer/payment/create")
   public CommonResult<Payment> create(Payment payment) {
       log.info("请求参数payment为：" + payment);
       return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
   }

   @GetMapping("/consumer/payment/get/{id}")
   public CommonResult getPayment(@PathVariable Long id){
       log.info("请求参数id为：" + id);
       //getForObject返回的是json串
       return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id ,CommonResult.class);
   }

    @GetMapping("/consumer/payment/discovery")
    public Object getDiscovery() {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/discovery"  ,Object.class);
    }

    @GetMapping("/consumer/payment/getEntity/{id}")
    public CommonResult getPaymentEntity(@PathVariable Long id){
       //getForEntity返回ResponseEntity对象，包含了响应中的一些重要信息，比如状态码、响应头、响应体等
      ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id ,CommonResult.class);
      if (entity.getStatusCode().is2xxSuccessful()) {
          log.info("code={},body={},headers={}",entity.getStatusCode(),entity.getBody(),entity.getHeaders());
          return entity.getBody();
      } else {
          return new CommonResult(404,"操作失败");
      }
   }


    @GetMapping("/consumer/payment/discoveryEntity")
    public Object getDiscoveryEntity() {
        ResponseEntity<Object> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/discovery"  ,Object.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            log.info("code={},body={},headers={}",entity.getStatusCode(),entity.getBody(),entity.getHeaders());
            return entity.getBody();
        } else {
            return new CommonResult(404,"操作失败");
        }
    }


    @PostMapping("/consumer/payment/createEntity")
    public CommonResult<Payment> createEntity(Payment payment) {
        log.info("请求参数payment为：" + payment);
        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create",payment, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            log.info("code={},body={},headers={}",entity.getStatusCode(),entity.getBody(),entity.getHeaders());
            return entity.getBody();
        } else {
            return new CommonResult<>(444,"操作失败");
        }
    }

    /**
     * 使用getForObject、getForEntity或postForEntity、postForObject调用微服务时请求方法一定要一直类似、
     * @return
     */
    @GetMapping("/consumer/payment/discoveryPost")
    public Object getPostDiscovery() {
        ResponseEntity<Object> entity = restTemplate.postForEntity(getUri() + "/payment/discovery"  ,"",Object.class);
        return entity.getBody();
    }

    @GetMapping("/consumer80/payment/lb")
    public String getPaymentLB(){

        return restTemplate.getForObject(PAYMENT_URL + "/payment/lb",String.class);
    }

    public URI getUri(){
        //根据服务名称获取所有的服务实例
        List<ServiceInstance> instances =  discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances == null || instances.size() <= 0){
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        return  serviceInstance.getUri();
    }

    @GetMapping("/consumer/zipkin")
    public String paymentZipkin(){
        return  restTemplate.getForObject(PAYMENT_URL + "/payment/zipkin",String.class);
    }
}
