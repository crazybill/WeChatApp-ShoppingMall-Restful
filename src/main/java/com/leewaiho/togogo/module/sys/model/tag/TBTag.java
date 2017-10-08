package com.leewaiho.togogo.module.sys.model.tag;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.leewaiho.togogo.common.base.model.BaseModel;
import com.leewaiho.togogo.common.jsonFormatter.CustomStringDeserializer;
import com.leewaiho.togogo.common.jsonFormatter.CustomStringSerializer;
import com.leewaiho.togogo.module.sys.model.product.TBProduct;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
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
    
    @Column(columnDefinition = "longtext")
    @JsonSerialize(using = CustomStringSerializer.class)
    @JsonDeserialize(using = CustomStringDeserializer.class)
    private String tagOptions;
    
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
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getTagOptions() {
        return tagOptions;
    }
    
    public void setTagOptions(String tagOptions) {
        this.tagOptions = tagOptions;
    }
    
    public Set<TBProduct> getProducts() {
        return products;
    }
    
    public void setProducts(Set<TBProduct> products) {
        this.products = products;
    }
    
    public enum Type {
        PRODUCT("商品", "product"), PRODUCT_CATEGORY("商品分类", "productCategory");
        private String name;
        private String value;
    
        Type(String name, String value) {
            this.name = name;
            this.value = value;
        }
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public String getValue() {
            return value;
        }
    
        public void setValue(String value) {
            this.value = value;
        }
    }
}
