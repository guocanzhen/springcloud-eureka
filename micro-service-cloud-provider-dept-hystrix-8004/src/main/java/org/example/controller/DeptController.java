package org.example.controller;

import lombok.extern.log4j.Log4j2;
import org.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/dept/hystrix")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/ok/{id}")
    public String deptInfoOk(@PathVariable("id") Integer id) {
        String result = deptService.deptInfoOk(id);
        log.info("端口号：" + serverPort + ",result；" + result);
        return result + "，端口号：" + serverPort;
    }

    /**
     * Hystrix 服务超时降级
     * @param id
     * @return
     */
    @GetMapping("timeout/{id}")
    public String deptInfoTimeout(@PathVariable("id") Integer id) {
        String result = deptService.deptInfoTimeout(id);
        log.info("端口号：" + serverPort + ",result；" + result);
        return result + "，端口号：" + serverPort;
    }

    /**
     * Hystrix 服务熔断
     * @param id
     * @return
     */
    @GetMapping("circuit/{id}")
    public String deptCircuitBreaker(@PathVariable("id") Integer id) {
        String result = deptService.deptCircuitBreaker(id);
        log.info("result：{}", result);
        return result;
    }

}
