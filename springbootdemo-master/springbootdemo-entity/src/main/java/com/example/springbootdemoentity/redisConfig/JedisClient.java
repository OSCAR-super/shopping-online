package com.example.springbootdemoentity.redisConfig;



public interface JedisClient {
    String set(String key, String value);

    String get(String key);

    Boolean exists(String key);

    Long expire(String key, int seconds);

    Long ttl(String key);

    Long incr(String key);

    Long hset(String key, String field, String value);

    String hget(String key, String field);

    Long hdel(String key, String... field);

    void setx(String key,int seconds,String value);
}
