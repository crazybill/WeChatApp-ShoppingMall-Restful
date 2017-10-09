package com.leewaiho.togogo.common.base.controller;

import com.leewaiho.togogo.common.dto.WebUser;
import com.leewaiho.togogo.common.pojo.Result;
import com.leewaiho.togogo.common.util.OAuth2Util;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.leewaiho.togogo.common.Const.BASE_PATH;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/10/9
 * Project togogo-shixun
 */
@RestController
@RequestMapping(BASE_PATH + "/login")
public class LoginController {
    
    @RequestMapping(method = RequestMethod.POST)
    public Result login(@RequestBody WebUser user) {
        OAuth2AccessToken accessToken = OAuth2Util.getAccessToken(user.getUsername(), user.getPassword());
        return Result.success(accessToken);
    }
}
