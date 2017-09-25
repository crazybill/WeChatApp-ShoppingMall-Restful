package com.leewaiho.togogo.common.base.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Id;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class BaseModel implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    
    @JsonProperty(value = "create_time", access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    
    @JsonProperty(value = "update_time", access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
    
    private int sort = 100; // 排序
    
    private int status = Status.NORMAL; // 1:正常 0:禁用
    
    public BaseModel() {
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
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
    
    public int getSort() {
        return sort;
    }
    
    public void setSort(int sort) {
        this.sort = sort;
    }
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "BaseModel{" +
                       "id='" + id + '\'' +
                       ", createTime=" + createTime +
                       ", updateTime=" + updateTime +
                       ", sort=" + sort +
                       ", status=" + status +
                       '}';
    }
    
    public class Status{
        public static final int NORMAL = 1; // 正常
        public static final int DISABLE = -1; // 禁用 未启用 未激活
        public static final int LOCKED = 0; // 锁定
    }
}
