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
    
    @RequestMapping(method = RequestMethod.GET, params = {"phone", "scope", "action"})
    public Result getRegisterCode(@RequestParam("phone") String phoneNumber,
                                  @RequestParam("scope") String scope,
                                  @RequestParam("action") String action) {
        return Result.success(ServiceCode.SUCCESS, smsService.getPhoneCode(phoneNumber, scope, action), "获取验证码成功");
    }
    
    @RequestMapping(method = RequestMethod.GET, params = {"phone", "scope", "action", "code"})
    public Result checkValidCode(@RequestParam("phone") String phoneNumber,
                                 @RequestParam("scope") String scope,
                                 @RequestParam("action") String action,
                                 @RequestParam("code") String code) {
        return Result.success(smsService.checkValidCode(phoneNumber, scope, action, code), "验证成功");
    }
    
}
