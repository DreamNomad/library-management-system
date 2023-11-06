package com.book.config;

import com.google.common.collect.ImmutableList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 允许的来源的请求
        config.setAllowedOriginPatterns(ImmutableList.of("*"));
        // 允许客户端携带凭证信息，如cookies
        config.setAllowCredentials(true);
        // 允许的HTTP方法 HttpMethod.POST
        config.setAllowedMethods(ImmutableList.of("GET", "POST", "PUT", "DELETE"));
        // 允许所有请求头
        config.addAllowedHeader("*");
        // 允许所有响应头暴露给客户端
        config.addExposedHeader("*");
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        // 为所有路径注册CORS配置
        configSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(configSource);
    }
}
