package com.book.controller.interceptor;

import com.book.common.Constants;
import com.book.context.UserContext;
import com.book.dao.redis.TokenCacheDao;
import com.book.entity.User;
import com.book.exception.ApiException;
import com.book.result.ResultCodeEnum;
import com.book.utils.JWTUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 用户登录拦截器
 *
 * @author k
 * @date 2023/06/23
 */
@Component
public class UserLoginInterceptor implements HandlerInterceptor {
    private final TokenCacheDao tokenCacheDao;

    @Autowired
    public UserLoginInterceptor(TokenCacheDao tokenCacheDao) {
        this.tokenCacheDao = tokenCacheDao;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response,@NonNull Object handler) throws Exception {
        //http的header中获得token
        String token = request.getHeader(JWTUtil.header);
        //token不存在
        if (StringUtils.isBlank(token)){
            throw new ApiException(ResultCodeEnum.USER_NOT_LOGIN);
        };
        if (Boolean.parseBoolean(request.getHeader(Constants.DELETE_TOKEN))){
            return tokenCacheDao.removeToken(token);
        }
        //验证token 从redis中取出
        User user = tokenCacheDao.getUserByToken(token);
        if (Objects.isNull(user)){
            throw new ApiException(ResultCodeEnum.TOKEN_EXPIRED);
        }
        UserContext.setUser(user);
        // TODO 可以自己添加更新token过期时间
        return true;
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull Object handler, Exception ex) throws Exception {
        UserContext.clear();
    }
}
