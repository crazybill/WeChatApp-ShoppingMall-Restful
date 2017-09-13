package com.leewaiho.togogo.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author leewaiho
 * @Email 791783391@qq.com
 * @Date 2017/8/26
 */
public class TimeUtil {
    public static final long SECOND = 1000;
    public static final long MINUTES = SECOND * 60;
    public static final long HOUR = MINUTES * 60;
    public static final long DAY = HOUR * 24;
    public static final SimpleDateFormat GMT = new SimpleDateFormat("EEE, MMM d, yyyy hh:mm:ss a z");
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    
    /**
     * 获取 yyyy-MM-dd HH:mm:ss 格式的时间字符串
     * @param date
     * @return
     */
    public static String getFormatDate(Date date) {
        return SIMPLE_DATE_FORMAT.format(date);
    }
    
    /**
     * 获取sdf格式的时间
     *
     * @param date 时间
     * @param sdf  格式
     * @return
     */
    public static String getFormatDate(Date date, SimpleDateFormat sdf) {
        return sdf.format(date);
    }
    
    /**
     * 返回 两个时间的时间差 单位为秒
     *
     * @param d1
     * @param d2
     * @return 相差的秒数
     */
    public static long getTimeDiff(Date d1, Date d2) {
        long diff = d1.getTime() - d2.getTime() >= 0 ? d1.getTime() - d2.getTime() : d2.getTime() - d1.getTime();
        return diff / SECOND;
        
    }
}
