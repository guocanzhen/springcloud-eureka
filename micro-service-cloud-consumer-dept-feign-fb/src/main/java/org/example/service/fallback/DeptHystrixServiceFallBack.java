package org.example.service.fallback;

import lombok.extern.log4j.Log4j2;
import org.example.service.DeptHystrixService;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class DeptHystrixServiceFallBack implements DeptHystrixService {

    @Override
    public String deptInfoOk(Integer id) {
        log.error("deptInfoOk fallback");
        return "--------------------系统繁忙，请稍后重试！（解耦回退方法触发）-----------------------";
    }

    @Override
    public String deptInfoTimeout(Integer id) {
        log.error("deptInfoTimeout fallback");
        return "--------------------系统繁忙，请稍后重试！（解耦回退方法触发）-----------------------";
    }
}
