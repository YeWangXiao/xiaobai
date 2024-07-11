package com.tianlan.tlproject.demos.Utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisUtils {


    @Autowired
    private static StringRedisTemplate stringRedisTemplate;




    //设置key和value
    public void setRedis(String key, String value) {
        //使用stringRedisTemplate的opsForValue方法设置key和value
        stringRedisTemplate.opsForValue().set(key, value);
    }

    //获取key对应的value
    public static String getRedis(String key) {
        //使用stringRedisTemplate的opsForValue方法获取key对应的value
        return stringRedisTemplate.opsForValue().get(key);
    }

    //删除key
    public void delete(String key) {
        //使用stringRedisTemplate删除key
        stringRedisTemplate.delete(key);
    }


    //判断key是否存在
    public boolean hasKey(String key) {
        //使用stringRedisTemplate判断key是否存在
        return stringRedisTemplate.hasKey(key);
    }





}
