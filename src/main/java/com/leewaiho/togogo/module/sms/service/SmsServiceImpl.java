package com.leewaiho.togogo.module.sms.service;

import com.aliyuncs.exceptions.ClientException;
import com.leewaiho.togogo.common.Const;
import com.leewaiho.togogo.common.exception.ServiceException;
import com.leewaiho.togogo.common.util.IdWorker;
import com.leewaiho.togogo.common.util.StringUtils;
import com.leewaiho.togogo.common.util.TimeUtil;
import com.leewaiho.togogo.module.sms.AliSmsClient;
import com.leewaiho.togogo.module.sms.dto.PhoneCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/29
 * Project togogo-shixun
 */
@Service
public class SmsServiceImpl implements SmsService {
    
    private static final Logger log = LoggerFactory.getLogger(SmsService.class);
    
    @Value("${sms.phoneCode.interval:60}")
    private int interval;
    @Autowired
    private AliSmsClient aliSmsClient;
    @Autowired
    private RedisTemplate redisTemplate;
    
    @Override
    public Object getCodeAndSend(String phoneNumber) {
        PhoneCode phoneCode = getPhoneCode(phoneNumber);
        return sendCode(phoneCode.getPhoneNumber(), phoneCode.getCode());
    }
    
    @Override
    public Object sendCode(String phoneNumber, String code) {
        try {
            return aliSmsClient.sendCode(phoneNumber, code);
        } catch (ClientException e) {
            throw new SecurityException(e.getMessage());
        }
    }
    
    @Override
    public PhoneCode getPhoneCode(@RequestParam("phone") String phoneNumber) {
        if (!StringUtils.isPhoneLegal(phoneNumber))
            throw new ServiceException(Const.ServiceCode.BADREQUEST, "输入的手机号码不合法");
        if (redisTemplate.hasKey(phoneNumber)) {
            PhoneCode code = getPhoneCodeByNumber(phoneNumber);
            long timeDiff = TimeUtil.getTimeDiff(new Date(), code.getCreateTime());
            if (timeDiff < 0)
                throw new ServiceException("时间校验异常");
            log.info("距离生成上次生成验证码已经过了: {}s", timeDiff);
            if (timeDiff < interval)
                throw new ServiceException(String.format("请勿频繁发送验证码请求,请等待 %d 秒后重试", interval - timeDiff));
        }
        String value = String.valueOf(IdWorker.getFlowIdWorkerInstance().nextId());
        String code = value.substring(value.length() - 4, value.length());
        log.info("生成的ID: {}", value);
        log.info("根据ID生成的验证码: {}", code);
        PhoneCode phoneCode = new PhoneCode(phoneNumber, code, new Date());
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(phoneCode.getPhoneNumber(), phoneCode, 5, TimeUnit.MINUTES);
        return phoneCode;
    }
    
    
    @Override
    public PhoneCode getPhoneCodeByNumber(String phoneNumber) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        if (!(valueOperations.get(phoneNumber) instanceof PhoneCode)) {
            deleteCode(phoneNumber);
            throw new ServiceException("验证码状态异常, 请重试!");
        }
        return (PhoneCode) valueOperations.get(phoneNumber);
        
    }
    
    @Override
    public boolean checkValidCode(String phoneNumber, String code) {
        if (!redisTemplate.hasKey(phoneNumber))
            throw new ServiceException("未查询到验证码,请重新获取验证码");
        PhoneCode phoneCode = getPhoneCodeByNumber(phoneNumber);
        if (!phoneCode.getCode().equals(code))
            throw new ServiceException("输入的验证码有误,请重试");
        deleteCode(phoneNumber);
        return true;
        
    }
    
    @Override
    public void deleteCode(String phoneNumber) {
        redisTemplate.opsForValue().getOperations().delete(phoneNumber);
    }
}
