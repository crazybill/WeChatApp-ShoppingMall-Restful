package com.leewaiho.togogo.module.sys.service;

import com.leewaiho.togogo.model.TSUser;

/**
 * @Author leewaiho
 * @Email 791783391@qq.com
 * @Date 2017/9/15
 */
public interface UserService {
    
    TSUser findByUsername(String username);
    
}
