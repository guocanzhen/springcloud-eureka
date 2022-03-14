package org.example.service;

import org.example.service.fallback.DeptHystrixServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * 服务绑定接口
 */
@Component
@FeignClient(value = "MICROSERVICECLOUDPROVIDERDEPTHYSTRIX", fallback = DeptHystrixServiceFallBack.class, path = "/dept/hystrix")
public interface DeptHystrixService {

    @GetMapping("/ok/{id}")
    String deptInfoOk(@PathVariable("id") Integer id);

    @GetMapping("/timeout/{id}")
    String deptInfoTimeout(@PathVariable("id") Integer id);
}
