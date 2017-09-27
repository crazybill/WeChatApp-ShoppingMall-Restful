package com.leewaiho.togogo.module.sys.service.user;

import com.leewaiho.togogo.common.base.service.BaseServiceImpl;
import com.leewaiho.togogo.common.exception.ServiceException;
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
        TSUser byUsername = userRepository.findByUsername(username);
        if (byUsername == null) {
            throw new ServiceException(String.format("不存在用户名为 %s 的用户", username));
        }
        return byUsername;
    }
    
    @Override
    public TSUser findByOpenId(String openId) {
        TSUser byOpenId = userRepository.findByOpenId(openId);
        if (byOpenId == null) {
            log.error("微信用户未注册 OpenId : {}", openId);
            throw new ServiceException(String.format("微信用户未注册"));
        }
        return byOpenId;
    }
}
