package com.leewaiho.togogo.config;

import com.leewaiho.togogo.common.interceptor.HttpRequestInterceptor;
import com.leewaiho.togogo.module.sys.security.Interceptor.OAuth2Interceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/24
 * Project togogo-shixun
 */
@Configuration
@EnableWebMvc
public class InterceptorRegisterConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpRequestInterceptor());
        registry.addInterceptor(new OAuth2Interceptor()).excludePathPatterns("/oauth/**");
        super.addInterceptors(registry);
    }
}