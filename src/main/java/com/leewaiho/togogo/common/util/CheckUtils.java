package com.leewaiho.togogo.common.util;

import com.leewaiho.togogo.common.exception.ServiceException;

import static com.leewaiho.togogo.common.Const.ServiceCode;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/10/1
 * Project togogo-shixun
 */
public class CheckUtils {
    
    public static boolean check(boolean condition, ServiceCode serviceCode, String message) {
        if (!condition)
            if (!StringUtils.isEmpty(message))
                throw new ServiceException(serviceCode, message);
            else
                throw new ServiceException(serviceCode);
        return true;
    }
    
    public static boolean check(boolean condition, ServiceCode serviceCode) {
        return check(condition, serviceCode, null);
    }
    
    public static boolean check(boolean condition, String message) {
        return check(condition, ServiceCode.BADREQUEST, message);
    }
    
    public static boolean check(boolean condition) {
        return check(condition, ServiceCode.BADREQUEST, null);
    }
    
}
