package com.leewaiho.togogo.module.sys.service.impl;

import com.leewaiho.togogo.mapper.TSUserMapper;
import com.leewaiho.togogo.model.TSUser;
import com.leewaiho.togogo.model.TSUserExample;
import com.leewaiho.togogo.module.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/15
 * Project togogo-shixun
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private TSUserMapper userMapper;
    
    @Override
    public TSUser findByUsername(String username) {
        TSUserExample example = new TSUserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<TSUser> userList = userMapper.selectByExample(example);
        if (userList == null || userList.isEmpty()) {
            return null;
        }
        return userList.get(0);
    }
}
