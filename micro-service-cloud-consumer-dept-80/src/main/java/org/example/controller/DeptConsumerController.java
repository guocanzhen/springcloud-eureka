package org.example.controller;

import lombok.extern.log4j.Log4j2;
import org.example.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 该 Controller 中定义的请求用于调用服务端提供的服务
 */
@RestController
@RequestMapping("/consumer/dept")
@Log4j2
public class DeptConsumerController {

    /**
     * 这种方式是直调用服务方的方法，根本没有用到 Spring Cloud 的服务
     * 在开启了@LoadBalanced负载均衡后，无法使用ip调通，而应该调服务
     */
//    private static final String REST_URL_PROVIDER_PREFIX = "http://localhost:8001/";

    /**
     * 面向微服务编程，即通过微服务的名称来获取调用地址
     * 使用注册到 Spring Cloud Eureka 服务注册中心中的服务，即 application.name
     */
    private static final String REST_URL_PROVIDER_PREFIX = "http://MICROSERVICECLOUDPROVIDERDEPT";

    /**
     * RestTemplate 是一种简单便捷的访问 restful 服务模板类，
     * 是 Spring 提供的用于访问 Rest 服务的客户端模板工具集，
     * 提供了多种便捷访问远程 HTTP 服务的方法
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取指定部门信息
     * @return
     */
    @GetMapping("get/{id}")
    public Dept get(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(REST_URL_PROVIDER_PREFIX + "/dept/get/" + id, Dept.class);
    }

    /**
     * 获取部门列表
     * @return
     */
    @GetMapping("list")
    public List<Dept> list() {
        return restTemplate.getForObject(REST_URL_PROVIDER_PREFIX + "/dept/list", List.class);
    }

}
