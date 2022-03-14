package org.example.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * 服务绑定接口
 */
@Component
@FeignClient(value = "MICROSERVICECLOUDPROVIDERDEPTHYSTRIX")
@RequestMapping("/dept/hystrix")
public interface DeptHystrixService {

    @GetMapping("/ok/{id}")
    String deptInfoOk(@PathVariable("id") Integer id);

    @GetMapping("timeout/{id}")
    String deptInfoTimeout(@PathVariable("id") Integer id);
}
