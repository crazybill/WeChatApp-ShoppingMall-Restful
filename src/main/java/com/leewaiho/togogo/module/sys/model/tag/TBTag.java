package com.leewaiho.togogo.module.sys.model.tag;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leewaiho.togogo.common.base.model.BaseModel;
import com.leewaiho.togogo.module.sys.model.product.TBProduct;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/20
 * Project togogo-shixun
 */
@Entity
@Table(name = "T_B_TAG")
public class TBTag extends BaseModel implements Serializable {
    
    private String name;
    
    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private Set<TBTagOption> tagOptions;
    
    @ManyToMany(mappedBy = "productTags")
    @JsonIgnore
    private Set<TBProduct> products;
    
    private String type;
    
    public TBTag() {
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Set<TBTagOption> getTagOptions() {
        return tagOptions;
    }
    
    public void setTagOptions(Set<TBTagOption> tagOptions) {
        this.tagOptions = tagOptions;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Set<TBProduct> getProducts() {
        return products;
    }
    
    public void setProducts(Set<TBProduct> products) {
        this.products = products;
    }
    
    public class Type{
        public static final String PRODUCT = "product";
    }
}
