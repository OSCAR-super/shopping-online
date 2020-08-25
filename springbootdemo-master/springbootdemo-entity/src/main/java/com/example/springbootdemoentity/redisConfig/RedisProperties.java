package com.example.springbootdemoentity.redisConfig;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


//依赖注入
@Component
//该注解用于读取配置文件中的属性，其中prefix表示前缀；
@ConfigurationProperties(prefix = "spring.redis.cluster")
public class RedisProperties {
    private int expireSeconds;
    private String nodes;
    private int commandTimeout;

    public int getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(int expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public int getCommandTimeout() {
        return commandTimeout;
    }

    public void setCommandTimeout(int commandTimeout) {
        this.commandTimeout = commandTimeout;
    }

    @Override
    public String toString() {
        return "RedisProperties{" +
                "expireSeconds=" + expireSeconds +
                ", nodes='" + nodes + '\'' +
                ", commandTimeout=" + commandTimeout +
                '}';
    }
}
