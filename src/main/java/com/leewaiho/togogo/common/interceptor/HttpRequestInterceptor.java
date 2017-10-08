package com.leewaiho.togogo.common.interceptor;

import com.leewaiho.togogo.common.util.IpAddressUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Map;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/10/8
 * Project togogo-shixun
 */
public class HttpRequestInterceptor extends HandlerInterceptorAdapter {
    
    private static final Logger log = LoggerFactory.getLogger(HttpRequestInterceptor.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        printHttpRequestMetaData(request);
        printHeadersMap(request);
        printParameterMaps(request);
        return super.preHandle(request, response, handler);
    }
    
    private void printHttpRequestMetaData(HttpServletRequest request) {
        log.info("来自IP: {} 的网络请求, HTTP Method: {}, 目标地址: {}", IpAddressUtil.getIpAddress(request), request.getMethod(), request.getRequestURL());
    }
    
    private void printHeadersMap(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            log.info(" {} : {}", headerName, request.getHeader(headerName));
        }
    }
    
    private void printParameterMaps(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap == null || parameterMap.size() == 0)
            log.info("没有传参数");
        else
            for (Map.Entry<String, String[]> stringEntry : parameterMap.entrySet()) {
                log.info("{} : {}", stringEntry.getKey(), stringEntry.getValue());
            }
    }
}
