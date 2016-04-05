package com.gaogandeng.test;

import com.gaogandeng.utils.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lanxing on 16-3-28.
 */

@RunWith(SpringJUnit4ClassRunner.class)   //相当于继承了SpringJUnit4ClassRunner
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-mybatis.xml", "classpath:redis-context.xml"})
public class RedisTest {
    private RedisService redisService;

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    @Test
    public void testGetRedis(){
        redisService.pushCmd("test", "haha");
//        System.out.println(redisService.popCmd("test"));
        redisService.pushCmd("test", "aaha");
        redisService.pushCmd("test", "caha");
        redisService.pushCmd("test", "baha");
        redisService.pushCmd("test", "daha");
        redisService.pushCmd("test", "faha");

        System.out.println(redisService.popCmd("test"));
        System.out.println(redisService.popCmd("test"));
        System.out.println(redisService.popCmd("test"));
        System.out.println(redisService.popCmd("test"));
        System.out.println(redisService.popCmd("test"));
        System.out.println(redisService.popCmd("test"));
    }

    @Test
    public void testMapRedis(){
        redisService.insertMap("map", "1234", "nnnn");
        System.out.println(redisService.getMapValue("map", "1234"));
        redisService.deleteMap("map", "1234");
    }
}
