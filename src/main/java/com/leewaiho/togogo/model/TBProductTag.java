package com.leewaiho.togogo.model;

import java.util.Date;

public class TBProductTag {
    private String id;
    
    private String productId;
    
    private Integer tagId;
    
    private Date createTime;
    
    private String content;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
    
    public String getProductId() {
        return productId;
    }
    
    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }
    
    public Integer getTagId() {
        return tagId;
    }
    
    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}