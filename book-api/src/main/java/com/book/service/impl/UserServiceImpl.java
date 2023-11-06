package com.book.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.book.dao.redis.TokenCacheDao;
import com.book.entity.User;
import com.book.dao.UserDao;
import com.book.entity.dto.LoginDto;
import com.book.exception.ApiException;
import com.book.result.ResultCodeEnum;
import com.book.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.utils.JWTUtil;
import com.book.utils.StringUtil;
import com.book.utils.SysUtil;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.CharSetUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.buf.CharsetUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author k
 * @since 2023-06-23
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {
    private final TokenCacheDao tokenCacheDao;
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, TokenCacheDao tokenCacheDao) {
        this.userDao = userDao;
        this.tokenCacheDao = tokenCacheDao;
    }

    @Override
    public boolean deleteByIds(String ids) {
        Iterable<String> split = Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split(ids);
        List<Integer> collect = StreamSupport.stream(split.spliterator(), false).map(Integer::parseInt).collect(Collectors.toList());
        return this.removeByIds(collect);
    }

    @Override
    public String login(LoginDto loginDto) {
        LambdaQueryChainWrapper<User> wrapper = new LambdaQueryChainWrapper<>(userDao).eq(User::getAccount, loginDto.getAccount());
        Optional<User> optionalUser = wrapper.oneOpt();
        // 账户不存在
        if (!optionalUser.isPresent()){
            throw new ApiException(ResultCodeEnum.USERNAME_NOT_EXIST);
        }
        User user1 = optionalUser.get();
        // 验证密码
        String encryptedPwd = SysUtil.encryptedPwd(loginDto.getPassword(), user1.getSalt());
        if (!StringUtils.equals(user1.getPassword(),encryptedPwd)){
            throw new ApiException(ResultCodeEnum.ERROR_INVALID_CREDENTIALS);
        }
        // 登录成功 添加token缓存
        String token = JWTUtil.createToken(StringUtil.nullToEmpty(user1.getId()));
        tokenCacheDao.addToken(token, user1);
        return token;
    }

    @Override
    public boolean register(LoginDto loginDto) {
        LambdaQueryChainWrapper<User> wrapper = new LambdaQueryChainWrapper<>(userDao).eq(User::getAccount, loginDto.getAccount());
        Optional<User> optionalUser = wrapper.oneOpt();
        // 用户名已存在
        if (optionalUser.isPresent()){
            throw new ApiException(ResultCodeEnum.USERNAME_EXIST);
        }
        // 加密密码
        User user = SysUtil.encryptedPwd(loginDto.getPassword());
        user.setAccount(loginDto.getAccount());
        return this.save(user);
    }

    @Override
    public boolean logout(String token) {
        return false;
    }

    @Override
    public boolean add(User user) {
        // 默认密码123456
        User user1 = SysUtil.encryptedPwd("123456");
        user.setPassword(user1.getPassword());
        user.setSalt(user1.getSalt());
        return this.save(user);
    }

    @Override
    public boolean logout() {
        return false;
    }
}
