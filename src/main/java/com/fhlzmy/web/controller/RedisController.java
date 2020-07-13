package com.fhlzmy.web.controller;


import com.fhlzmy.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/redisTemplate")
public class RedisController extends BaseController {


    @Autowired
    private StringRedisTemplate redisTemplate;


    @RequestMapping(value = "/para")
    public @ResponseBody String env(String para) throws Exception{
        redisTemplate.opsForValue().set("key", para);

        logger.info("redis 参数:" + para);

        String str = redisTemplate.opsForValue().get("key");
        return str;
    }


}
