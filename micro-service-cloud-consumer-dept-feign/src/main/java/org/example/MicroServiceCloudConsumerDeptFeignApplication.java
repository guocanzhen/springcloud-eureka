package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Spring Cloud 应用在启动时，OpenFeign 会扫描标有 @FeignClient 注解的接口生成代理，并注人到 Spring 容器中。
 */

@SpringBootApplication
// 开启 OpenFeign 功能
@EnableFeignClients
//启用 Hystrix
@EnableHystrix
public class MicroServiceCloudConsumerDeptFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroServiceCloudConsumerDeptFeignApplication.class, args);
    }
}
