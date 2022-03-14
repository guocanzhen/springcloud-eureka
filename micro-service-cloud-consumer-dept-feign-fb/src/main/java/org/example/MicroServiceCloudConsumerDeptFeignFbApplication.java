package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
// 开启 OpenFeign 功能
@EnableFeignClients
//启用 Hystrix
@EnableHystrix
public class MicroServiceCloudConsumerDeptFeignFbApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroServiceCloudConsumerDeptFeignFbApplication.class, args);
    }
}
