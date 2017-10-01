package com.leewaiho.togogo.module.sms.controller;

import com.leewaiho.togogo.common.Const.ServiceCode;
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
    
    @RequestMapping(method = RequestMethod.GET, params = "phone")
    public Result getValidCode(@RequestParam("phone") String phoneNumber) {
        return Result.success(ServiceCode.SUCCESS ,smsService.getPhoneCode(phoneNumber), "获取验证码成功");
    }
    
    @RequestMapping(method = RequestMethod.GET, params = {"phone", "code"})
    public Result checkValidCode(@RequestParam("phone") String phoneNumber, @RequestParam("code") String code) {
        return Result.success(smsService.checkValidCode(phoneNumber, code), "验证成功");
    }
    
}
