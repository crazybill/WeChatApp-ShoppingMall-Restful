package com.leewaiho.togogo.module.sys.controller;

import com.leewaiho.togogo.common.base.controller.BaseController;
import com.leewaiho.togogo.common.pojo.Result;
import com.leewaiho.togogo.module.sys.model.TSUser;
import com.leewaiho.togogo.module.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.leewaiho.togogo.common.base.controller.BaseController.BASE_PATH;

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
    
    public Result findById(@PathVariable String id) {
        return Result.success(userService.findById(id));
    }
    
    public Result listPage(Pageable pageable) {
        return Result.success(userService.findAll(pageable));
    }
    
    public Result<TSUser> save(TSUser tsUser) {
        return Result.success(userService.save(tsUser));
    }
    
    public Result delete(@PathVariable  String id) {
        userService.delete(id);
        return Result.success(null);
    }
}
