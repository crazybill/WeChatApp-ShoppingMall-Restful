package com.leewaiho.togogo.module.sms.service;

import com.aliyuncs.exceptions.ClientException;
import com.leewaiho.togogo.module.sms.AliSmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/29
 * Project togogo-shixun
 */
@Service
public class SmsServiceImpl implements SmsService {
    
    @Autowired
    private AliSmsClient aliSmsClient;
    
    
    @Override
    public Object sendCode(String phoneNumber, String code) {
        try {
            return aliSmsClient.sendCode(phoneNumber, code);
        } catch (ClientException e) {
            throw new SecurityException(e.getMessage());
        }
    }
    
}
