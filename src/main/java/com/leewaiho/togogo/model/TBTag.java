package com.leewaiho.togogo.model;

import java.util.Date;

public class TBTag {
    private String id;
    
    private Integer status;
    
    private String type;
    
    private String name;
    
    private String description;
    
    private Date createTime;
    
    private String createBy;
    
    private String content;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
    
    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public String getCreateBy() {
        return createBy;
    }
    
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}