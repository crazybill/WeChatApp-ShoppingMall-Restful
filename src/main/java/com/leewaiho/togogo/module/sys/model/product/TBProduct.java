package com.leewaiho.togogo.module.sys.model.product;

import com.leewaiho.togogo.common.base.model.BaseModel;
import com.leewaiho.togogo.module.sys.model.image.TSImage;
import com.leewaiho.togogo.module.sys.model.tag.TBTag;

import javax.persistence.*;
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
    
    @ManyToMany
    @JoinTable(name = "T_B_PRODUCT_TAG",
            joinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "TAG_ID", referencedColumnName = "id")})
    private Set<TBTag> productTags;
    
    @ManyToMany
    @JoinTable(name = "T_B_PRODUCT_IMAGE",
            joinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "IMAGE_ID", referencedColumnName = "id")}
    )
    private Set<TSImage> productImages;
    
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
    
    public Set<TBTag> getProductTags() {
        return productTags;
    }
    
    public void setProductTags(Set<TBTag> productTags) {
        this.productTags = productTags;
    }
    
    public Set<TSImage> getProductImages() {
        return productImages;
    }
    
    public void setProductImages(Set<TSImage> productImages) {
        this.productImages = productImages;
    }
    
}
