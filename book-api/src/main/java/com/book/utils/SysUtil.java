package com.book.utils;

import com.book.entity.User;
import com.google.common.base.Charsets;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

public class SysUtil {

    public static User encryptedPwd(String pwd) {
        String salt = RandomStringUtils.random(10, Charsets.UTF_8.toString());
        // 使用SHA-256算法对密码和盐值进行加密
        return new User().setSalt(salt).setPassword(DigestUtils.sha256Hex(pwd.concat(salt)));
    }

    public static String encryptedPwd(String pwd, String salt) {
        return DigestUtils.sha256Hex(pwd.concat(salt));
    }

}
