package com.leewaiho.togogo.module.sys.service.impl;

import com.leewaiho.togogo.common.exception.ServiceException;
import com.leewaiho.togogo.common.service.impl.BaseServiceImpl;
import com.leewaiho.togogo.module.sys.model.TSUser;
import com.leewaiho.togogo.module.sys.repository.TSUserRepository;
import com.leewaiho.togogo.module.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author leewaiho
 * @Email 791783391@qq.com
 * @Date 2017/9/15
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<TSUser, String> implements UserService {
    
    @Autowired
    private TSUserRepository userRepository;
    
    @Override
    public TSUser findByUsername(String username) {
        TSUser user = userRepository.findByUsername(username);
        if (user == null) {
            String message = String.format("用户 %s 不存在", username);
            log.error(message);
            throw new ServiceException(message);
        }
        return user;
    }
}
