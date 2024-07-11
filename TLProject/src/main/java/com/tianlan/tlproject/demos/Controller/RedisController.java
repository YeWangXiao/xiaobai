package com.tianlan.tlproject.demos.Controller;


import com.tianlan.tlproject.demos.Utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/redis")
public class RedisController {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @GetMapping("/getRedisKey")
    @ResponseBody
    public String getRedis(@RequestParam("key") String key) {
        String value = stringRedisTemplate.opsForValue().get(key);
        return value;
    }


    @GetMapping("/setRedis")
    @ResponseBody
    public String setRedis() {
        stringRedisTemplate.opsForValue().set("test", "test");
        return "true";
    }

    @GetMapping("/getRedis")
    @ResponseBody
    public String getRedis() {
        String test = stringRedisTemplate.opsForValue().get("test");
        return test;
    }
}
