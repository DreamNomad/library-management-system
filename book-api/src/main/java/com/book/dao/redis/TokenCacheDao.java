package com.book.dao.redis;

import com.book.utils.JSONUtil;
import com.book.entity.User;
import com.book.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@Repository
public class TokenCacheDao {
    private static final String KEY_PATTERN = "user:%s";

    //@Resource(name = "redisTemplate")
    //@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    //private ValueOperations<String, String> operations;

    @Autowired
    private StringRedisTemplate operations;

    private static String buildKey(String token) {
        return String.format(KEY_PATTERN, token);
    }

    public User getUserByToken(String token) {
        String key = buildKey(token);
        String value = operations.opsForValue().get(key);
        return JSONUtil.parseObject(value, User.class);
    }

    public void addToken(String token, User object) {
        String key = buildKey(token);
        String value = JSONUtil.toJsonString(object);
        operations.opsForValue().set(key, value, JWTUtil.expiration, TimeUnit.SECONDS);
    }

    public boolean removeToken(String token) {
        return Boolean.TRUE.equals(operations.delete(buildKey(token)));
    }
}
