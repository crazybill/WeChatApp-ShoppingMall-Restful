package com.leewaiho.togogo.module.wechat.dto;

import java.io.Serializable;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/29
 * Project togogo-shixun
 */
public class RegisterObject implements Serializable {
    
    private String username; // 微信用户名称
    
    private String password; // 设置的密码
    
    private String avatarUrl; // 微信用户的头像
    
    private byte gender; // 微信用户的性别
    
    private String mobilePhone; // 手机号码
    
    private String validCode; // 验证码
    
    private String wxCode; // 用于获取OpenId的验证码
    
    public RegisterObject() {
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
    
    public String getAvatarUrl() {
        return avatarUrl;
    }
    
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
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
    
    public String getValidCode() {
        return validCode;
    }
    
    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }
    
    public String getWxCode() {
        return wxCode;
    }
    
    public void setWxCode(String wxCode) {
        this.wxCode = wxCode;
    }
}
