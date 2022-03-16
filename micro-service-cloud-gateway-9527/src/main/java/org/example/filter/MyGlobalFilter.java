package org.example.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * 自定义全局网关过滤器（GlobalFilter）
 */
@Component
@Log4j2
public class MyGlobalFilter implements GlobalFilter, Ordered {

    /**
     * 全局网关过滤器
     * 参数 uname 不能为空
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("进入自定义的全局过滤器 MyGlobalFilter：{}", new Date());
        String uName = exchange.getRequest().getQueryParams().getFirst("uname");
        if (uName == null) {
            log.info("参数 uname 不能为空！");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    /**
     * 过滤器的顺序，0 表示第一个
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
