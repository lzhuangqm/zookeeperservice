package com.cn.zookeeper.config;

/**
 * 配制类
 */
import org.apache.curator.framework.*;
import org.apache.curator.retry.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;

@Configuration
@PropertySources(value = {@PropertySource("classpath:zookeeper.properties")})

public class ZookeeperConfig {

    @Value("${zookeeper.server}")
    private String server;
    @Value("${zookeeper.enabled}")
    private String enabled;
    @Value("${zookeeper.namespace}")
    private String namespace;
    @Value("${zookeeper.digest}")
    private String digest;
    @Value("${zookeeper.sessionTimeoutMs}")
    private String sessionTimeoutMs;
    @Value("${zookeeper.connectionTimeoutMs}")
    private String connectionTimeoutMs;
    @Value("${zookeeper.maxRetries}")
    private String maxRetries;
    @Value("${zookeeper.baseSleepTimeMs}")
    private String baseSleepTimeMs;

    public ZookeeperConfig() {
    }

    public String getServer() {
        return server;
    }

    public String getEnabled() {
        return enabled;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getDigest() {
        return digest;
    }

    public String getSessionTimeoutMs() {
        return sessionTimeoutMs;
    }

    public String getConnectionTimeoutMs() {
        return connectionTimeoutMs;
    }

    public String getMaxRetries() {
        return maxRetries;
    }

    public String getBaseSleepTimeMs() {
        return baseSleepTimeMs;
    }

    /**
     * 初始化zookeeper客户端
     */
    @Bean(initMethod = "start",destroyMethod="close")
    public CuratorFramework init() {
        return CuratorFrameworkFactory.newClient(
                getServer(),
                Integer.parseInt(getSessionTimeoutMs()),
                Integer.parseInt(getConnectionTimeoutMs()),
                new RetryNTimes(Integer.parseInt(getMaxRetries()), Integer.parseInt(getBaseSleepTimeMs())));
    }
}
