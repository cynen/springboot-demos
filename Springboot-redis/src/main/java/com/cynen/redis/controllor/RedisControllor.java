package com.cynen.redis.controllor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RedisControllor {

    @Autowired
    private  RedisTemplate redisTemplate;

    @RequestMapping(value = "/set/{value}",method = RequestMethod.GET)
    public String setValue(@PathVariable String value){
        redisTemplate.opsForValue().set("key",value);
        return  "完成!";
    }

    @RequestMapping(value="/get",method= RequestMethod.GET)
    public String getValue( ){
        String code = (String) redisTemplate.opsForValue().get("key");
        return code;

    }

    @RequestMapping(value="/test",method= RequestMethod.GET)
    public void test( ){
        redisTemplate.opsForValue().set("key","测试一下");

        String value = (String) redisTemplate.opsForValue().get("key");

        System.out.println("获取到的值: " + value);

    }

}
