# springCloud-springCloud-alibaba
springCloud第二季加springCloud alibaba

为什么要学习springCloud第二季？
1.第一季中有很多组件处于停更不停用的状态，可以用，但是不推荐使用。
  服务注册与发现：Eureka（停更）使用 zookeeper、Consul、nacos（推荐使用alibaba的nacos）；
  负载均衡：Ribbon（推荐使用），还可用LoadBlancer；
  服务调用：Feign（停更），推荐使用OpenFeign；
  服务降级：Hytrix（停更），resilience4j、sentinel（推荐使用alibaba的）；
  服务网关：zuul（停更）、zuul2（估计胎死腹中）、geteway（推荐使用）；
  服务配置中：springCloud config（停更），nacos（推荐使用）；
  服务总线：springCloud Bus（停更），nacos（推荐使用）
  
2.可以看出nocas是重中之重。
   
