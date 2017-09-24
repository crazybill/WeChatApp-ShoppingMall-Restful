package com.leewaiho.togogo.module.sys.service.user;

import com.leewaiho.togogo.common.base.service.BaseServiceImpl;
import com.leewaiho.togogo.module.sys.model.user.TSUser;
import com.leewaiho.togogo.module.sys.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/15
 * Project togogo-shixun
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<TSUser> implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public TSUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
}
