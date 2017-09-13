package com.leewaiho.togogo.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author leewaiho
 * @Email 791783391@qq.com
 * @Date 2017/8/22
 */
public class IpAddressUtil {
    
    private final static Logger log = LoggerFactory.getLogger(IpAddressUtil.class);
    
    /**
     * 根据HTTP请求获取IP地址
     *
     * @param request Http请求
     * @return IP地址 or ""
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = null;
        try {
            ip = request.getHeader("x-forwarded-for");
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
            log.error("IpAddress ERROR ", e);
        }
        return ip;
    }
    
    /**
     * 自动获取HTTP请求上下文然后从中提取IP地址
     *
     * @return 成功为IP地址 失败为 ""
     */
    public static String getIpAddress() {
        String ip = "";
        
        try {
            HttpServletRequest httpServletRequest = HttpContextUtil.getRequest();
            ip = getIpAddress(httpServletRequest);
        } catch (Exception e) {
            log.error("自动获取http请求失败");
        }
        
        return ip;
    }
    
    
}
