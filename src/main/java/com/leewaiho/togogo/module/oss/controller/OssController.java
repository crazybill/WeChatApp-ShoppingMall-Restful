package com.leewaiho.togogo.module.oss.controller;

import com.leewaiho.togogo.common.pojo.Result;
import com.leewaiho.togogo.module.oss.pojo.CallbackBody;
import com.leewaiho.togogo.module.oss.service.OssService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/10/4
 * Project togogo-shixun
 */
@RestController
@RequestMapping("/api/oss")
public class OssController {
    
    private static final Logger log = LoggerFactory.getLogger(OssController.class);
    @Autowired
    private OssService ossService;
    
    @RequestMapping(value = "/imageToken", method = RequestMethod.GET)
    public Result createToken() {
        return Result.success(ossService.getToken());
    }
    
    @RequestMapping(value = "/upload/callback", method = RequestMethod.POST)
    public Result callback(@RequestBody CallbackBody callbackBody, @RequestParam("uid") String uid) {
        log.info("哇靠,你又偷传什么小黄片? 用户ID : {}", uid);
        return Result.success(callbackBody);
    }
}
