package com.book.config;


import com.book.controller.interceptor.UserLoginInterceptor;
import com.book.utils.JWTUtil;
import com.google.common.base.Splitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * web mvc配置
 *
 * @author k
 * @date 2023/06/23
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final UserLoginInterceptor userLoginInterceptor;

    @Autowired
    public WebMvcConfig(UserLoginInterceptor userLoginInterceptor) {
        this.userLoginInterceptor = userLoginInterceptor;
    }

    /**
     * 添加拦截器
     *
     * @param registry 注册表
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePatterns = Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .splitToList(JWTUtil.excludePath);
        registry.addInterceptor(userLoginInterceptor)
                .addPathPatterns("/**")
                // 白名单
                .excludePathPatterns(excludePatterns);
    }

    //@Override
    //public void addCorsMappings(@NonNull CorsRegistry registry) {
    //    // 表示为所有路径启用CORS
    //    registry.addMapping("/**")
    //            // 表示允许来自任何源的请求
    //            .allowedOriginPatterns("*")
    //            // 表示允许浏览器在跨域请求中发送凭据（如cookies和HTTP认证信息）。如果设置为true，则响应中的Access-Control-Allow-Origin头不能为*，必须指定具体的源
    //            //.allowCredentials(true)
    //            // 表示允许哪些HTTP方法
    //            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
    //            // 表示预检请求的结果可以被缓存多长时间（以秒为单位）
    //            .maxAge(3600);
    //}
}
