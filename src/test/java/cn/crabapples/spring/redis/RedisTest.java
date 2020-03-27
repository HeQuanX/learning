package cn.crabapples.spring.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

/**
 * TODO redis单元测试
 *
 * @author Mr.He
 * 1/29/20 3:25 PM
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name root
 */
@SpringBootTest
@ActiveProfiles("dev")
//@ContextConfiguration("classpath:application-dev.yml")
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTest {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Test
    public void case1(){
        System.err.println(redisTemplate.opsForValue().setIfAbsent("crabapples","hello,world",3, TimeUnit.MINUTES));
        System.err.println(redisTemplate.opsForValue().get("crabapples"));
    }
}
