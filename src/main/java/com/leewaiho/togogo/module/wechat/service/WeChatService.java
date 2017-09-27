package com.leewaiho.togogo.module.wechat.service;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/27
 * Project togogo-shixun
 */
public interface WeChatService {
    
    Object wechatLogin(String code);
    
    String getOpenId(String code);

}
