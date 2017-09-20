package com.leewaiho.togogo.module.sys.controller;

import com.leewaiho.togogo.common.base.BaseController;
import com.leewaiho.togogo.common.pojo.Result;
import com.leewaiho.togogo.module.sys.model.TSUser;
import com.leewaiho.togogo.module.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.leewaiho.togogo.common.base.BaseController.BASE_PATH;

/**
 * @Author leewaiho
 * @Email 791783391@qq.com
 * @Date 2017/9/15
 */
@RestController
@RequestMapping(BASE_PATH + "/users")
public class UserController extends BaseController<TSUser, String> {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return Result.success(userService.findById(id));
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public Result listPage(Pageable pageable) {
        return Result.success(userService.findAll(pageable));
    }
    
    
    @RequestMapping(method = RequestMethod.POST)
    public Result<TSUser> save(TSUser tsUser) {
        return Result.success(userService.save(tsUser));
    }
    
    @RequestMapping(method = RequestMethod.DELETE)
    public Result delete(String id) {
        userService.delete(id);
        return Result.success(null);
    }
}
