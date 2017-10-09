package com.leewaiho.togogo.module.sys.controller;

import com.leewaiho.togogo.common.pojo.Result;
import com.leewaiho.togogo.module.sys.security.SecurityUtils;
import com.leewaiho.togogo.module.sys.security.dto.UserInfo;
import com.leewaiho.togogo.module.sys.service.My.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
@RequestMapping(BASE_PATH + "/my")
public class MyController {
    
    @Autowired
    private MyService myService;
    
    @RequestMapping(method = RequestMethod.GET, value = "/orders")
    public Result getMyOrders(Pageable pageable) {
        UserInfo userInfo = SecurityUtils.getUser();
        return Result.success(myService.getMyOrders(userInfo.getUser(), pageable));
    }
}
