package org.example.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.example.service.DeptService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service("deptService")
public class DeptServiceImpl implements DeptService {

    /**
     * hystrix 熔断器示例 ok
     * @param id
     * @return
     */
    @Override
    public String deptInfoOk(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " deptInfoOk, id = " + id;
    }


    /**
     * hystrix 熔断器超时案例
     * 一旦该方法失败并抛出了异常信息后，会自动调用  @HystrixCommand 注解标注的 fallbackMethod 指定的方法
     *
     * 参数 fallbackMethod 属性用于指定降级方法。
     * 参数 execution.isolation.thread.timeoutInMilliseconds 用于设置自身调用超时时间的峰值，峰值内可以正常运行，否则执行降级方法
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "dept_TimeoutHandler",
            // 规定 5 秒钟以内就不报错，正常运行，超过 5 秒就报错，调用指定的方法
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")})
    @Override
    public String deptInfoTimeout(Integer id) {
        int outTime = id;
        try {
            TimeUnit.SECONDS.sleep(outTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + " deptInfoTimeout, id = " + id + ",耗时：" + outTime;
    }

    /**
     *  当服务出现故障后，调用该方法给出友好提示
     * @return
     */
    public String dept_TimeoutHandler(Integer id) {
        return  "系统繁忙请稍后再试！"+"线程池：" + Thread.currentThread().getName() + "  deptInfoTimeout, id = " + id;
    }

    /**
     * Hystrix 熔断机制案例
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "deptCircuitBreaker_fallback",
            commandProperties = {
                    //以下参数在 HystrixCommandProperties 类中有默认配置
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //是否开启熔断器
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds",value = "1000"), //统计时间窗口期，ms
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //统计时间窗内请求次数
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //休眠时间窗口期，ms
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //在统计时间窗口期以内，请求失败率达到 60% 时进入熔断状态
            })
    @Override
    public String deptCircuitBreaker(Integer id) {
        if (id < 0) {
            //当传入的 id 为负数时，抛出异常，调用降级方法
            throw new RuntimeException("id 不能是负数！");
        }
        String serialNum = String.valueOf(Math.random());
        return Thread.currentThread().getName() + "\t" + "调用成功，随机数为：" + serialNum;
    }

    //deptCircuitBreaker 的降级方法
    public String deptCircuitBreaker_fallback(Integer id) {
        return "降级方法- id 不能是负数,请稍后重试!\t id:" + id;
    }


}
