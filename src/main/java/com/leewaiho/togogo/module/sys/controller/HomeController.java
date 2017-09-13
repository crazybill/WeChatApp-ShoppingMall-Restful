package com.leewaiho.togogo.module.sys.controller;

import com.leewaiho.togogo.common.pojo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author leewaiho
 * @Email 791783391@qq.com
 * @Date 2017/9/13
 */
@RestController
public class HomeController {
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Result home() {
        return new Result("操作成功", true, "hello world");
    }
}
