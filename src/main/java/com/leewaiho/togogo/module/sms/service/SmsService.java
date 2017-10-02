package com.leewaiho.togogo.module.sms.service;

import com.leewaiho.togogo.module.sms.dto.PhoneCode;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/29
 * Project togogo-shixun
 */
public interface SmsService {
    
    String WECHAT = "wechat";
    String REGISTER = "register";
    String LOGIN = "login";
    
    /**
     * 生成验证码并发送给指定的手机号码
     *
     * @param phoneNumber
     * @return
     */
    String getCodeAndSend(String phoneNumber, String scope, String action);
    
    /**
     * 发送指定验证码到指定手机号码
     *
     * @param phoneNumber 手机号码
     * @param code        验证码
     * @return
     */
    String sendCode(String phoneNumber, String code);
    
    /**
     * 生成手机验证码并存储到Redis中
     *
     * @param phoneNumber 需要生成验证码的手机号码
     * @param scope 生效区域
     * @param action 执行动作
     * @return 生成的手机验证码对象
     */
    PhoneCode getPhoneCode(String phoneNumber, String scope, String action);
    
    /**
     * 根据手机号码从Redis中取出验证码
     *
     * @param phoneNumber
     * @param scope 生效区域
     * @param action 执行动作
     * @return 查询得到的手机验证码对象
     */
    PhoneCode getPhoneCodeByNumber(String phoneNumber, String scope, String action);
    
    /**
     * 验证指定手机号码的验证码是否正确
     *
     * @param phoneNumber 验证的手机号码
     * @param scope 验证码作用域
     * @param action 验证码作用动作
     * @param code 接收到的验证码
     * @return 成功则返回true否则抛出对应异常
     */
    boolean checkValidCode(String phoneNumber, String scope, String action, String code);
    
    /**
     * 根据手机号码删除Redis中的验证码
     *
     * @param phoneNumber 需要删除验证码的手机号码
     * @param scope 生效区域
     * @param action 执行动作
     */
    void deleteCode(String phoneNumber, String scope, String action);
}
