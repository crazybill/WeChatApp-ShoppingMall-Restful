package com.leewaiho.togogo.module.wechat.controller;

import com.leewaiho.togogo.common.Const.ServiceCode;
import com.leewaiho.togogo.common.pojo.Result;
import com.leewaiho.togogo.module.sms.service.SmsService;
import com.leewaiho.togogo.module.sys.model.user.TSUser;
import com.leewaiho.togogo.module.wechat.dto.RegisterObject;
import com.leewaiho.togogo.module.wechat.service.WeChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/15
 * Project togogo-shixun
 */
@RestController
@RequestMapping("/api/wechat")
public class WeChatController {
    
    private Logger log = LoggerFactory.getLogger(WeChatController.class);
    
    @Autowired
    private WeChatService weChatService;
    @Autowired
    private SmsService smsService;
    
    @RequestMapping(method = RequestMethod.GET, value = "/login", params = "code")
    public Result loginWeChat(@RequestParam("code") String code) {
        log.info("接收到的Code: {}", code);
        return Result.success(weChatService.loginWeChat(code));
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public Result regWeChatUser(@RequestBody RegisterObject registerObject) {
        smsService.checkValidCode(registerObject.getMobilePhone(), SmsService.WECHAT, SmsService.REGISTER, registerObject.getValidCode());
        TSUser user = weChatService.registerOnWeChat(registerObject);
        smsService.deleteCode(user.getMobilePhone(), SmsService.WECHAT, SmsService.REGISTER);
        log.info("注册成功: {}", user);
        return Result.success(ServiceCode.REGISTER, null, null);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public Result getRegCode(@RequestParam("phone") String phoneNumber) {
        return Result.success(smsService.getPhoneCode(phoneNumber, SmsService.WECHAT, SmsService.REGISTER));
    }
}
