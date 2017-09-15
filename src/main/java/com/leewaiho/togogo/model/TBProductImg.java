package com.leewaiho.togogo.model;

import java.util.Date;

public class TBProductImg {
    private String id;
    
    private String productId;
    
    private String imgId;
    
    private String imgUrl;
    
    private Integer sort;
    
    private Date createTime;
    
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
    
    public String getImgId() {
        return imgId;
    }
    
    public void setImgId(String imgId) {
        this.imgId = imgId == null ? null : imgId.trim();
    }
    
    public String getImgUrl() {
        return imgUrl;
    }
    
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }
    
    public Integer getSort() {
        return sort;
    }
    
    public void setSort(Integer sort) {
        this.sort = sort;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}