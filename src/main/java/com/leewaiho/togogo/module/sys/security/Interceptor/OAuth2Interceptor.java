package com.leewaiho.togogo.module.sys.security.Interceptor;

import com.leewaiho.togogo.common.util.IpAddressUtil;
import com.leewaiho.togogo.module.sys.security.SecurityUtils;
import com.leewaiho.togogo.module.sys.security.dto.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/24
 * Project togogo-shixun
 */
public class OAuth2Interceptor extends HandlerInterceptorAdapter {
    
    private static final Logger log = LoggerFactory.getLogger(OAuth2Interceptor.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("来自IP: {} 的网络请求, HTTP Method: {}, 目标地址: {}", IpAddressUtil.getIpAddress(request), request.getMethod(), request.getRequestURL());
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap == null || parameterMap.size() == 0)
            log.info("没有传参数");
        else
            for (Map.Entry<String, String[]> stringEntry : parameterMap.entrySet()) {
                log.info("{} : {}", stringEntry.getKey(), stringEntry.getValue());
            }
        UserInfo user = SecurityUtils.getUser();
        if (user != null)
            log.info(user.toString());
        return super.preHandle(request, response, handler);
    }
}
