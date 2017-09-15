package com.leewaiho.togogo.module.sys.controller;

import com.leewaiho.togogo.common.pojo.Result;
import com.leewaiho.togogo.module.sys.model.TSUser;
import com.leewaiho.togogo.module.sys.repository.TSUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author leewaiho
 * @Email 791783391@qq.com
 * @Date 2017/9/15
 */
@RestController
public class UserController {
    @Autowired
    private TSUserRepository userRepository;
    
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Result<TSUser> test() throws Exception {
        return Result.success(userRepository.findAll());
    }
}
