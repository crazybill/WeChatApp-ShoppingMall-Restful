package com.leewaiho.togogo.module.sms.service;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/29
 * Project togogo-shixun
 */
public interface SmsService {
    
    Object sendCode(String phoneNumber, String code);

}
