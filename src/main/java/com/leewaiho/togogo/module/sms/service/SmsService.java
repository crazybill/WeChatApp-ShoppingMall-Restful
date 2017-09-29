package com.leewaiho.togogo.module.sms.service;

import com.leewaiho.togogo.module.sms.dto.PhoneCode;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/29
 * Project togogo-shixun
 */
public interface SmsService {
    
    /**
     * 生成验证码并发送给指定的手机号码
     *
     * @param phoneNumber
     * @return
     */
    Object getCodeAndSend(String phoneNumber);
    
    /**
     * 发送指定验证码到指定手机号码
     *
     * @param phoneNumber 手机号码
     * @param code        验证码
     * @return
     */
    Object sendCode(String phoneNumber, String code);
    
    /**
     * 生成手机验证码并存储到Redis中
     *
     * @param phoneNumber 需要生成验证码的手机号码
     * @return 生成的手机验证码对象
     */
    PhoneCode getPhoneCode(@RequestParam("phone") String phoneNumber);
    
    /**
     * 根据手机号码从Redis中取出验证码
     *
     * @param phoneNumber
     * @return 查询得到的手机验证码对象
     */
    PhoneCode getPhoneCodeByNumber(String phoneNumber);
    
    /**
     * 验证指定手机号码的验证码是否正确
     *
     * @param phoneNumber 验证的手机号码
     * @param code        接收到的验证码
     * @return 成功则返回true否则抛出对应异常
     */
    boolean checkValidCode(String phoneNumber, String code);
    
    /**
     * 根据手机号码删除Redis中的验证码
     *
     * @param phoneNumber 需要删除验证码的手机号码
     */
    void deleteCode(String phoneNumber);
}
