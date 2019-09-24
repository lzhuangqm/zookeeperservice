package com.cn;

/**
 * Zookeeper测试
 */
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cloud.client.circuitbreaker.*;
import org.springframework.cloud.client.discovery.*;
import org.springframework.cloud.client.loadbalancer.*;
import org.springframework.context.annotation.*;
import org.springframework.web.client.*;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker

public class ZookeeperserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperserviceApplication.class, args);
    }

    /**
     * 向程序的ioc注入一个bean: restTemplate;并通过@LoadBalanced注解表明这个restRemplate开启负载均衡的功能。
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
