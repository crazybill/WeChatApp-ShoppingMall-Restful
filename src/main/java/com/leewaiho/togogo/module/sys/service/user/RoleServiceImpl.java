package com.leewaiho.togogo.module.sys.service.user;

import com.leewaiho.togogo.common.base.service.BaseServiceImpl;
import com.leewaiho.togogo.module.sys.model.user.TSRole;
import com.leewaiho.togogo.module.sys.repository.user.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/24
 * Project togogo-shixun
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<TSRole> implements RoleService {
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Override
    public TSRole findByRoleKey(String roleKey) {
        return roleRepository.findByRoleKey(roleKey);
    }
    
}
