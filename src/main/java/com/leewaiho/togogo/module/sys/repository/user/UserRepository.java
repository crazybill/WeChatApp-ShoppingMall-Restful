package com.leewaiho.togogo.module.sys.repository.user;

import com.leewaiho.togogo.common.base.repository.BaseRepository;
import com.leewaiho.togogo.module.sys.model.user.TSUser;
import org.springframework.stereotype.Repository;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/18
 * Project togogo-shixun
 */
@Repository
public interface UserRepository extends BaseRepository<TSUser> {
    
    TSUser findByUsername(String username);
}
