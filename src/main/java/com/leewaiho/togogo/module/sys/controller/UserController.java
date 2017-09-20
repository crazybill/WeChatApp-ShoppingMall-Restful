package com.leewaiho.togogo.module.sys.controller;

import com.leewaiho.togogo.common.base.controller.BaseController;
import com.leewaiho.togogo.module.sys.model.TSUser;
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

}
