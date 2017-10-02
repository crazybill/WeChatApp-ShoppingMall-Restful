package com.leewaiho.togogo.module.sms.service;

import com.aliyuncs.exceptions.ClientException;
import com.leewaiho.togogo.common.Const.ServiceCode;
import com.leewaiho.togogo.common.components.SpringRedisCache;
import com.leewaiho.togogo.common.exception.ServiceException;
import com.leewaiho.togogo.common.util.CheckUtils;
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

import java.util.Date;

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
    @Autowired
    private SpringRedisCache redisCache;
    
    @Override
    public String getCodeAndSend(String phoneNumber, String scope, String action) {
        PhoneCode phoneCode = getPhoneCode(phoneNumber, scope, action);
        return sendCode(phoneCode.getPhoneNumber(), phoneCode.getCode());
    }
    
    @Override
    public String sendCode(String phoneNumber, String code) {
        try {
            return aliSmsClient.sendCode(phoneNumber, code);
        } catch (ClientException e) {
            throw new SecurityException(e.getMessage());
        }
    }
    
    public PhoneCode getPhoneCode(String phoneNumber, String scope, String action) {
        CheckUtils.check(StringUtils.isPhoneLegal(phoneNumber), "手机格式不合法");
        String key = redisCache.getKey(phoneNumber, scope, action);
        if (redisTemplate.hasKey(key)) {
            PhoneCode code = getPhoneCodeByNumber(phoneNumber, scope, action);
            long timeDiff = TimeUtil.getTimeDiff(new Date(), code.getCreateTime());
            if (timeDiff < 0)
                throw new ServiceException(ServiceCode.UNKNOWED, "时间校验异常");
            log.info("距离生成上次生成验证码已经过了: {}s", timeDiff);
            if (timeDiff < interval)
                throw new ServiceException(String.format("请勿频繁发送验证码请求,请等待 %d 秒后重试", interval - timeDiff));
        }
        String value = String.valueOf(IdWorker.getFlowIdWorkerInstance().nextId());
        String code = value.substring(value.length() - 4, value.length());
        log.info("生成的ID: {}", value);
        log.info("根据ID生成的验证码: {}", code);
        PhoneCode phoneCode = new PhoneCode(phoneNumber, code, new Date());
        redisCache.putCacheWithExpireTime(key, phoneCode, 5 * 60);
        return phoneCode;
    }
    
    
    @Override
    public PhoneCode getPhoneCodeByNumber(String phoneNumber, String scope, String action) {
        String key = redisCache.getKey(phoneNumber, scope, action);
        ValueOperations valueOperations = redisTemplate.opsForValue();
        if (!(valueOperations.get(key) instanceof PhoneCode)) {
            deleteCode(phoneNumber, scope, action);
            throw new ServiceException("验证码状态异常, 请重试!");
        }
        return redisCache.getCache(key, PhoneCode.class);
    }
    
    @Override
    public boolean checkValidCode(String phoneNumber, String scope, String action, String code) {
        String key = redisCache.getKey(phoneNumber, scope, action);
        if (!redisTemplate.hasKey(key))
            throw new ServiceException(ServiceCode.NOTFOUND, "未查询到验证码,请重新获取验证码");
        PhoneCode phoneCode = getPhoneCodeByNumber(phoneNumber, scope, action);
        if (!phoneCode.getCode().equals(code))
            throw new ServiceException(ServiceCode.BADREQUEST, "输入的验证码有误,请重试");
        return true;
        
    }
    
    @Override
    public void deleteCode(String phoneNumber, String scope, String action) {
        redisTemplate.opsForValue().getOperations().delete(redisCache.getKey(phoneNumber, scope, action));
    }
}
