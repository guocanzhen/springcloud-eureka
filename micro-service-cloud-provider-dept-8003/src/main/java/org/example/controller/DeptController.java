package org.example.controller;

import lombok.extern.log4j.Log4j2;
import org.example.entity.Dept;
import org.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private DiscoveryClient client;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "get/{id}")
    public Dept get(@PathVariable("id") int id) {
        return deptService.get(id);
    }

    @GetMapping(value = "list")
    public List<Dept> list() {
        return deptService.selectAll();
    }

}
