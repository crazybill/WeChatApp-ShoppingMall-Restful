package com.leewaiho.togogo.module.sys.model.order;

import com.leewaiho.togogo.common.base.model.BaseModel;
import com.leewaiho.togogo.module.sys.model.product.TBProduct;
import com.leewaiho.togogo.module.sys.model.tag.TBTagOption;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

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
    private TBOrder order;
    
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
    private TBProduct product;
    
    
    @ManyToMany
    @JoinTable(
            name = "T_B_ORDER_ITEM_TAG",
            joinColumns = @JoinColumn(name = "ITEM_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TAG_OPTION_ID", referencedColumnName = "ID")
    )
    private Set<TBTagOption> tagOptions;
    
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
    
    public Set<TBTagOption> getTagOptions() {
        return tagOptions;
    }
    
    public void setTagOptions(Set<TBTagOption> tagOptions) {
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
    
    @Override
    public String toString() {
        return "TBOrderItem{" +
                       "order=" + order +
                       ", product=" + product +
                       ", tagOptions=" + tagOptions +
                       ", count=" + count +
                       ", price=" + price +
                       "} " + super.toString();
    }
}
