package com.leewaiho.togogo.module.sys.model.product;

import com.leewaiho.togogo.common.base.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/20
 * Project togogo-shixun
 */
@Entity
@Table(name = "T_B_PRODUCT")
public class TBProduct extends BaseModel implements Serializable {
    
    private String name;
    
    private BigDecimal basePrice;
    
    private String description;
    
    @OneToMany(mappedBy = "product")
    private Set<TBProductTag> productTags;
    
    public TBProduct() {
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public BigDecimal getBasePrice() {
        return basePrice;
    }
    
    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Set<TBProductTag> getProductTags() {
        return productTags;
    }
    
    public void setProductTags(Set<TBProductTag> productTags) {
        this.productTags = productTags;
    }
    
    @Override
    public String toString() {
        return "TBProduct{" +
                       "name='" + name + '\'' +
                       ", basePrice=" + basePrice +
                       ", description='" + description + '\'' +
                       ", productTags=" + productTags +
                       "} " + super.toString();
    }
}
