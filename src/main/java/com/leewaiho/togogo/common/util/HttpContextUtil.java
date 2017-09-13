package com.leewaiho.togogo.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author leewaiho
 * @Email 791783391@qq.com
 * @Date 2017/8/22
 */
public class HttpContextUtil {
    private static final Logger log = LoggerFactory.getLogger(HttpContextUtil.class);
    
    public static ServletRequestAttributes getServletRequestAttributes() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            log.error("无法获取http请求");
            throw new NullPointerException("无法获取到Http请求");
        }
        return requestAttributes;
    }
    
    public static HttpServletRequest getRequest() {
        return getServletRequestAttributes().getRequest();
    }
    
    public static HttpServletResponse getResponse() {
        return getServletRequestAttributes().getResponse();
    }
}
