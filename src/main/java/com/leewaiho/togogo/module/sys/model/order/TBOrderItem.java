package com.leewaiho.togogo.module.sys.model.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.leewaiho.togogo.common.base.model.BaseModel;
import com.leewaiho.togogo.common.jsonFormatter.CustomStringDeserializer;
import com.leewaiho.togogo.common.jsonFormatter.CustomStringSerializer;
import com.leewaiho.togogo.module.sys.model.product.TBProduct;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/21
 * Project togogo-shixun
 */
@Entity
@Table(name = "T_B_ORDER_ITEM")
public class TBOrderItem extends BaseModel implements Serializable {
    
    @ManyToOne
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")
    @JsonIgnore
    private TBOrder order;
    
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
    private TBProduct product;
    
    
    @Column(columnDefinition = "longtext")
    @JsonSerialize(using = CustomStringSerializer.class)
    @JsonDeserialize(using = CustomStringDeserializer.class)
    private String tagOptions;
    
    private int count = 1;
    
    private BigDecimal price;
    
    public TBOrderItem() {
    }
    
    public TBOrder getOrder() {
        return order;
    }
    
    public void setOrder(TBOrder order) {
        this.order = order;
    }
    
    public TBProduct getProduct() {
        return product;
    }
    
    public void setProduct(TBProduct product) {
        this.product = product;
    }
    
    public String getTagOptions() {
        return tagOptions;
    }
    
    public void setTagOptions(String tagOptions) {
        this.tagOptions = tagOptions;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
}
