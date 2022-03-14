package org.example.controller;

import lombok.extern.log4j.Log4j2;
import org.example.service.DeptHystrixService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/consumer/dept/hystrix/fb")
@Log4j2
public class HystrixConsumerController {

    @Resource
    private DeptHystrixService deptHystrixService;

    @GetMapping("ok/{id}")
    public String deptInfoOk(@PathVariable("id") Integer id) {
        String s = deptHystrixService.deptInfoOk(id);
        log.info(s);
        return s;
    }

    @GetMapping("timeout/{id}")
    public String deptInfoTimeout(@PathVariable("id") Integer id) {
        String s = deptHystrixService.deptInfoTimeout(id);
        log.info(s);
        return s;
    }
}
