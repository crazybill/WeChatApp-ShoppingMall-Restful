package com.leewaiho.togogo.module.sys.service;

import com.leewaiho.togogo.module.sys.model.TSUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author leewaiho
 * @Email 791783391@qq.com
 * @Date 2017/9/15
 */
public interface UserService {
    
    TSUser findByUsername(String username);
    
    Page<TSUser> findAll(Pageable pageable);
}
