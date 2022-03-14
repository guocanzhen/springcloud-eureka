package org.example.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.log4j.Log4j2;
import org.example.service.DeptHystrixService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/consumer/dept/hystrix")
@Log4j2
//全局的服务降级方法
//注意：降级（FallBack）方法必须与其对应的业务方法在同一个类中，否则无法生效。
//注意：全局降级方法的优先级较低，只有业务方法没有指定其降级方法时，服务降级时才会触发全局回退方法。
//若业务方法指定它自己的回退方法，那么在服务降级时，就只会直接触发它自己的回退方法，而非全局回退方法。
@DefaultProperties(defaultFallback = "deptGlobalFallbackMethod")
public class HystrixConsumerController {

    @Resource
    private DeptHystrixService deptHystrixService;

    @GetMapping("ok/{id}")
    public String deptInfoOk(@PathVariable("id") Integer id) {
        String s = deptHystrixService.deptInfoOk(id);
        log.info(s);
        return s;
    }

    /**
     * 在客户端进行降级
     * @param id
     * @return
     */
//    @HystrixCommand(fallbackMethod = "deptTimeoutHandler") // 为该请求指定专属的回退方法
    @HystrixCommand // 使用全局降级回退方法
    @GetMapping("timeout/{id}")
    public String deptInfoTimeout(@PathVariable("id") Integer id) {
        String s = deptHystrixService.deptInfoTimeout(id);
        log.info(s);
        return s;
    }

    public String deptTimeoutHandler(Integer id) {
        log.info("deptInfo_Timeout 出错，服务已被降级！");
        return "服务端系统繁忙，请稍后再试！（客户端 deptInfo_Timeout 专属的回退方法触发），入参：" + id;
    }

    /**
     * 全局的 fallback 方法，
     * 回退方法必须和 hystrix 的执行方法在相同类中
     * @DefaultProperties(defaultFallback = "deptGlobalFallbackMethod") 类上注解，请求方法上使用 @HystrixCommand 注解
     * @return
     */
    public String deptGlobalFallbackMethod() {
        return "运行出错或服务端系统繁忙，请稍后再试！（客户端全局回退方法触发）";
    }
}
