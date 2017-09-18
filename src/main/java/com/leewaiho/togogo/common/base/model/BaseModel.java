package com.leewaiho.togogo.common.base.model;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/18
 * Project togogo-shixun
 */
@MappedSuperclass
public class BaseModel implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Date createTime;
    
    private Date updateTime;
    
    private int status = 1; // 1:正常 0:禁用
    
    public BaseModel() {
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public Date getUpdateTime() {
        return updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
}
