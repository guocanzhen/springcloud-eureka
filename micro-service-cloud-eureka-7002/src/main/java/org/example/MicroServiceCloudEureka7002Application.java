package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MicroServiceCloudEureka7002Application {

    public static void main(String[] args) {
        SpringApplication.run(MicroServiceCloudEureka7002Application.class, args);
    }
}
