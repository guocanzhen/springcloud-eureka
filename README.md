主工程

springcloud服务注册中心eureka ：7001、7002、7003

springcloud负载均衡、服务调用ribbon、restTemplate ：dept-80

springcloud服务调用openfeign ：dept-feign

Hystrix 服务端服务降级 ：dept-hystrix-8004

Hystrix 客户端服务降级：全局的服务降级方法；指定专属的回退方法 ：dept-feign

Hystrix 客户端服务降级：openFeign Hystrix 解耦降级逻辑 ：dept-feign-fb

Hystrix 服务熔断 ： dept-hystrix-8004

Hystrix 故障监控：dept-hystrix-8004、dept-hystrix-dashboard-9002

Spring Cloud Gateway - Predicate 断言，GatewayFilter 网关过滤器 ： gateway-9527