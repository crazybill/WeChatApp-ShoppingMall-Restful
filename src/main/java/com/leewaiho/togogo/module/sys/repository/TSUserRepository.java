package com.leewaiho.togogo.module.sys.repository;

import com.leewaiho.togogo.common.repository.BaseRepository;
import com.leewaiho.togogo.module.sys.model.TSUser;
import org.springframework.stereotype.Repository;

/**
 * @Author leewaiho
 * @Email 791783391@qq.com
 * @Date 2017/9/15
 */
@Repository
public interface TSUserRepository extends BaseRepository<TSUser, String> {
    
    TSUser findByUsername(String username);
    
}
