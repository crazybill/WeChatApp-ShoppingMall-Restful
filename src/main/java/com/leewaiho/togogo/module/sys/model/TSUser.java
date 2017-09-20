package com.leewaiho.togogo.module.sys.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.leewaiho.togogo.common.base.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/18
 * Project togogo-shixun
 */

@Entity
@Table(name = "T_S_USER")
public class TSUser extends BaseModel {
    
    private String openId;
    
    private String username;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    
    private byte gender = Gender.MALE; // 0:女 1:男 2:未知
    
    private String mobilePhone;
    
    private String email;
    
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
    
    public String toString() {
        return "TSUser{" +
                       "openId='" + openId + '\'' +
                       ", username='" + username + '\'' +
                       ", password='" + password + '\'' +
                       ", gender=" + gender +
                       ", mobilePhone='" + mobilePhone + '\'' +
                       ", email='" + email + '\'' +
                       "} " + super.toString();
    }
    
    public class Gender {
        public static final int FEMALE = 0; // 女性
        public static final int MALE = 1; // 男性
    }
}
