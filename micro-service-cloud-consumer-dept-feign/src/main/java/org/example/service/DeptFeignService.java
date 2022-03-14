package org.example.service;

import org.example.entity.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 使用 @FeignClient 注解实现对服务接口的绑定
 * 在 @FeignClient 注解中，value 属性的取值为：服务提供者的服务名，
 * 即服务提供者配置文件（application.yml）中 spring.application.name 的取值。
 * 接口中定义的每个方法都与服务提供者（即 micro-service-cloud-provider-dept-8001 等）中 Controller 定义的服务方法对应。
 */
//添加为容器内的一个组件
@Component
// 服务提供者提供的服务名称，即 application.name
@FeignClient(value = "MICROSERVICECLOUDPROVIDERDEPT")
@RequestMapping("/dept")
public interface DeptFeignService {

    /**
     * 对应服务提供者（8001、8002、8003）Controller 中定义的方法
     * @return
     */
    @GetMapping("get/{id}")
    Dept get(@PathVariable("id") Integer id);

    /**
     * 对应服务提供者（8001、8002、8003）Controller 中定义的方法
     * @return
     */
    @GetMapping("list")
    List<Dept> list();

    /**
     * OpenFeign 是如何进行超时控制的
     * @return
     */
    @GetMapping("/feign/timeout")
    String deptFeignTimeout();
}
