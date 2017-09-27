package com.leewaiho.togogo.module.sys.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.leewaiho.togogo.common.base.model.BaseModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/18
 * Project togogo-shixun
 */

@Entity
@Table(name = "T_S_USER")
public class TSUser extends BaseModel implements Serializable {
    
    private String openId;
    
    private String username;
    
    private String avatarUrl;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    
    private byte gender = Gender.MALE; // 0:女 1:男 2:未知
    
    private String mobilePhone;
    
    private String email;
    
    @ManyToMany
    @JoinTable(
            name = "T_S_USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
    )
    private Set<TSRole> roles;
    
    public TSUser() {
    }
    
    public String getOpenId() {
        return openId;
    }
    
    public void setOpenId(String openId) {
        this.openId = openId;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public byte getGender() {
        return gender;
    }
    
    public void setGender(byte gender) {
        this.gender = gender;
    }
    
    public String getMobilePhone() {
        return mobilePhone;
    }
    
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Set<TSRole> getRoles() {
        return roles;
    }
    
    public void setRoles(Set<TSRole> roles) {
        this.roles = roles;
    }
    
    public String getAvatarUrl() {
        return avatarUrl;
    }
    
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
    
    public class Gender {
        public static final int FEMALE = 0; // 女性
        public static final int MALE = 1; // 男性
    }
    
    @Override
    public String toString() {
        return "TSUser{" +
                       "openId='" + openId + '\'' +
                       ", username='" + username + '\'' +
                       ", avatarUrl='" + avatarUrl + '\'' +
                       ", gender=" + gender +
                       ", mobilePhone='" + mobilePhone + '\'' +
                       ", email='" + email + '\'' +
                       "} " + super.toString();
    }
}
