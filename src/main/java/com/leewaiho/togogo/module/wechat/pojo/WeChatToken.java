package com.leewaiho.togogo.module.wechat.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author leewaiho
 * @Email 791783391@qq.com
 * @Date 2017/9/13
 */
public class WeChatToken implements Serializable {
    
    private String accessToken;
    private Date createTime;
    private Date expiresTime;
    
    public WeChatToken() {
    }
    
    public String getAccessToken() {
        return accessToken;
    }
    
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public Date getExpiresTime() {
        return expiresTime;
    }
    
    public void setExpiresTime(Date expiresTime) {
        this.expiresTime = expiresTime;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        WeChatToken that = (WeChatToken) o;
        
        if (accessToken != null ? !accessToken.equals(that.accessToken) : that.accessToken != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        return expiresTime != null ? expiresTime.equals(that.expiresTime) : that.expiresTime == null;
    }
    
    @Override
    public int hashCode() {
        int result = accessToken != null ? accessToken.hashCode() : 0;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (expiresTime != null ? expiresTime.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString() {
        return "WeChatToken{" +
                       "accessToken='" + accessToken + '\'' +
                       ", createTime=" + createTime +
                       ", expiresTime=" + expiresTime +
                       '}';
    }
}
