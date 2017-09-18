package com.leewaiho.togogo.module.sys.service.impl;

import com.leewaiho.togogo.common.base.service.BaseServiceImpl;
import com.leewaiho.togogo.module.sys.model.TSUser;
import com.leewaiho.togogo.module.sys.repository.UserRepository;
import com.leewaiho.togogo.module.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/15
 * Project togogo-shixun
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<TSUser, String> implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public TSUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
}
