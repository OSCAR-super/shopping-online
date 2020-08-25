package com.example.springbootdemoconsumer.controller;


import com.example.springbootdemoentity.redisConfig.JedisClientCluster;
import com.example.springbootdemoentity.redisConfig.RedisConfig;
import com.example.springbootdemoentity.redisConfig.RedisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;


@RestController
public class RedisController {
    @Autowired
    private RedisProperties redisProperties;

    @Autowired
    private RedisConfig redisConfig;

    @Autowired
    private JedisClientCluster jedisClientCluster;

    @RequestMapping(value = "getRedisValue")
    public String getRedisValue(){
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
        jedis.del("1");
        //jedis.set("1", "start");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get("1"));

        return "ok";
    }
}
