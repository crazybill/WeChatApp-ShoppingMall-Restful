package com.leewaiho.togogo.module.wechat.api;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/15
 * Project togogo-shixun
 */
public interface WeChatApi {
    /**
     * 接口地址: https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
     *
     * @param code
     * @return
     */
    Object code2Session(String code);
}
