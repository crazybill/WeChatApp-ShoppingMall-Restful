package com.leewaiho.togogo.module.sys.security;

import com.leewaiho.togogo.module.sys.model.user.TSUser;
import com.leewaiho.togogo.module.sys.repository.user.UserRepository;
import com.leewaiho.togogo.module.sys.security.dto.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/23
 * Project togogo-shixun
 */
@Service
public class CustomUserDetailService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        TSUser user = userRepository.findByMobilePhone(phoneNumber);
        return new UserInfo(user);
    }
}
