package org.example.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 将 RestTemplate 注入到容器中
 */

@Configuration
public class ConfigBean {

    @Bean // 将 RestTemplate 注入到容器中
    @LoadBalanced // 在客户端使用 RestTemplate 请求服务端时，开启负载均衡（Ribbon）
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * 负载均衡策略
     * 默认：轮询
     * RoundRobinRule-轮询、RandomRule-随机、RetryRule-重试、WeightedResponseTimeRule-权重
     * BestAvailableRule-先过滤点故障或失效的服务实例，然后再选择并发量最小的服务实例。
     * AvailabilityFilteringRule-先过滤掉故障或失效的服务实例，然后再选择并发量较小的服务实例。
     * ZoneAvoidanceRule-默认的负载均衡策略，综合判断服务所在区域（zone）的性能和服务（server）的可用性，来选择服务实例。在没有区域的环境下，该策略与轮询（RandomRule）策略类似。
     * @return
     */
    @Bean
    public IRule myRule() {
        // 随机策略
        return new RandomRule();
    }

}
