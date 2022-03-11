package org.example.controller;

import lombok.extern.log4j.Log4j2;
import org.example.entity.Dept;
import org.example.service.DeptFeignService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/consumer/dept")
public class DeptConsumerController {

    @Resource
    private DeptFeignService deptFeignService;

    @GetMapping("/get/{id}")
    public Dept get(@PathVariable("id") Integer id) {
        return deptFeignService.get(id);
    }

    @GetMapping("/list")
    public List<Dept> list() {
        return deptFeignService.list();
    }

    /**
     * OpenFeign 是如何进行超时控制的
     * @return
     */
    @GetMapping("/feign/timeout")
    public String deptFeignTimeout() {
        // openFeign-ribbon 客户端一般默认等待一秒钟，超过该时间就会报错
        return deptFeignService.deptFeignTimeout();
    }

}
