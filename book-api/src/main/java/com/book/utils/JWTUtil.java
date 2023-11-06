package com.book.utils;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JWTUtil {
    /** token头部 */
    public static String header;
    /** token前缀 */
    public static String prefix;
    /** 签名密钥 */
    public static String secret;
    /** 到期时间 */
    public static long expiration;
    /** 拦截器白名单 */
    public static String excludePath;

    /**
     * 创建令牌
     *
     * @param id id
     * @return {@link String}
     */
    public static String createToken(String id){
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("id", id);
        JwtBuilder jwtBuilder = Jwts.builder()
                // 设置有效载荷
                .setClaims(map)
                // 设置签发时间
                .setIssuedAt(new Date())
                // 采用HS256方式签名，key就是用来签名的秘钥
                .signWith(SignatureAlgorithm.HS256, secret);
        return prefix.concat(jwtBuilder.compact());
    }

    public static String validateToken(String token) {
        try {
            Jws<String> stringJws = Jwts.parser().setSigningKey(secret).parsePlaintextJws(token.replace(prefix, StringUtils.EMPTY));
            return stringJws.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setHeader(String header) {
        JWTUtil.header = header;
    }

    public void setPrefix(String prefix) {
        JWTUtil.prefix = prefix;
    }

    public void setSecret(String secret) {
        JWTUtil.secret = secret;
    }

    public void setExpiration(long expiration) {
        JWTUtil.expiration = expiration;
    }

    public void setExcludePath(String excludePath) {
        JWTUtil.excludePath = excludePath;
    }
}
