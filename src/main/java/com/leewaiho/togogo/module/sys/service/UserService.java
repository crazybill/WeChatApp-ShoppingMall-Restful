package com.leewaiho.togogo.module.sys.service;


import com.leewaiho.togogo.common.base.service.BaseService;
import com.leewaiho.togogo.module.sys.model.TSUser;

/**
 * @Author leewaiho
 * @Email 791783391@qq.com
 * @Date 2017/9/15
 */
public interface UserService extends BaseService<TSUser> {
    
    TSUser findByUsername(String username);
    
}
