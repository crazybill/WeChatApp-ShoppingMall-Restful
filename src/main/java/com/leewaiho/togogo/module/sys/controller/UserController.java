package com.leewaiho.togogo.module.sys.controller;

import com.leewaiho.togogo.common.base.controller.BaseController;
import com.leewaiho.togogo.common.pojo.Result;
import com.leewaiho.togogo.module.sys.model.user.TSUser;
import com.leewaiho.togogo.module.sys.security.SecurityUtils;
import com.leewaiho.togogo.module.sys.security.dto.UserInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.leewaiho.togogo.common.base.controller.BaseController.BASE_PATH;

/**
 * @Author leewaiho
 * @Email 791783391@qq.com
 * @Date 2017/9/15
 */
@RestController
@RequestMapping(BASE_PATH + "/users")
public class UserController extends BaseController<TSUser> {
    
    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public Result whoami() {
        UserInfo user = SecurityUtils.getUser();
        log.info("用户信息: {}", user);
        return Result.success(user.getUser());
    }

}
