package com.leewaiho.togogo.module.wechat.controller;

import com.leewaiho.togogo.common.pojo.Result;
import com.leewaiho.togogo.module.wechat.WeChat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    private WeChat weChat;
    
    @RequestMapping(method = RequestMethod.GET)
    public Result getToken() {
        log.info(weChat.getWeChatToken().toString());
        return Result.success(weChat.getWeChatToken());
    }
    
    @RequestMapping(method = RequestMethod.GET, params = "code")
    public Result code2info(@RequestParam("code") String code) {
        log.info("接收到的Code: {}", code);
        return Result.success(weChat.code2Session(code));
    }
}
