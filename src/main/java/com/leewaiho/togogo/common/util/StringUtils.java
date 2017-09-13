package com.leewaiho.togogo.common.util;

/**
 * @Author leewaiho
 * @Email 791783391@qq.com
 * @Date 2017/9/13
 */
public class StringUtils {
    
    public static boolean isEmpty(String string) {
        if (string == null || string.length() == 0)
            return true;
        return false;
    }
    
}
