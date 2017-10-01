package com.leewaiho.togogo.module.sms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/29
 * Project togogo-shixun
 */
public class PhoneCode implements Serializable {
    
    private String phoneNumber;
    private String code;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    
    public PhoneCode() {
    }
    
    
    public PhoneCode(String phoneNumber, String code, Date createTime) {
        this.phoneNumber = phoneNumber;
        this.code = code;
        this.createTime = createTime;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Override
    public String toString() {
        return "PhoneCode{" +
                       "phoneNumber='" + phoneNumber + '\'' +
                       ", code='" + code + '\'' +
                       ", createTime=" + createTime +
                       '}';
    }
}
