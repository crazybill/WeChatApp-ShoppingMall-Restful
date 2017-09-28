package com.leewaiho.togogo.module.sms.controller;

import com.leewaiho.togogo.common.pojo.Result;
import com.leewaiho.togogo.module.sms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/29
 * Project togogo-shixun
 */
@RestController
@RequestMapping("/api/sms")
public class SmsController {
    
    @Autowired
    private SmsService smsService;
    
    @RequestMapping(method = RequestMethod.POST)
    public Result sendCode(@RequestParam("phone") String phoneNumber) {
        return Result.success(smsService.sendCode(phoneNumber, "520520"));
    }
    
}
