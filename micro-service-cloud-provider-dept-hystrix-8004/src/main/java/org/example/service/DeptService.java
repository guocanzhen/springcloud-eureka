package org.example.service;

public interface DeptService {

    /**
     * hystrix 熔断器示例 ok
     * @param id
     * @return
     */
    String deptInfoOk(Integer id);

    /**
     * hystrix 熔断器超时案例
     * @param id
     * @return
     */
    String deptInfoTimeout(Integer id);

    /**
     * Hystrix 熔断机制案例
     * @return
     */
    String deptCircuitBreaker(Integer id);
}
