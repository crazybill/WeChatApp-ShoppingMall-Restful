package com.leewaiho.togogo.module.sys.security.dto;

import com.leewaiho.togogo.module.sys.model.user.TSRole;
import com.leewaiho.togogo.module.sys.model.user.TSUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/23
 * Project togogo-shixun
 */
public class UserInfo implements UserDetails, Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private TSUser user;
    
    public UserInfo(TSUser user) {
        this.user = user;
    }
    
    public TSUser getUser() {
        return user;
    }
    
    public void setUser(TSUser user) {
        this.user = user;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        if (user.getRoles() != null) {
            for (TSRole role : user.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getKey()));
            }
            return authorities;
        }
        return null;
    }
    
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    
    @Override
    public String getUsername() {
        return user.getUsername();
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return user.getStatus() != 0;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return user.getStatus() == 1;
    }
    
    @Override
    public String toString() {
        return "UserInfo{" +
                       "user=" + user +
                       '}';
    }
}
