package com.book;

import com.book.dao.redis.TokenCacheDao;
import com.book.entity.User;
import com.google.common.base.Charsets;
import com.google.common.primitives.Bytes;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class TestRedis {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private TokenCacheDao userCacheDao;

    @Test
    public void testStringSetKey() {
        //stringRedisTemplate.opsForValue().set("yunai", "shuai");
        userCacheDao.addToken("1111",new User().setId(1).setName("11122233"));
    }

    @Test
    public void testStringSetKey2() {
        //stringRedisTemplate.opsForValue().set("yunai", "shuai");
        User user = userCacheDao.getUserByToken("2222");
        System.out.println(user);
    }

    @Test
    public void m1() {
        String password = "myPassword123";
        String salt = RandomStringUtils.random(10, Charsets.UTF_8.toString());
        // 使用SHA-256算法对密码和盐值进行加密
        String hashedPassword = DigestUtils.sha256Hex(password + salt);
        String hashedPassword1 = DigestUtils.sha256Hex(password + salt);

        System.out.println("原密码：" + password);
        System.out.println("盐值：" + salt);
        System.out.println("加密后的密码：" + hashedPassword);
        System.out.println("加密后的密码：" + hashedPassword1);
    }
}
