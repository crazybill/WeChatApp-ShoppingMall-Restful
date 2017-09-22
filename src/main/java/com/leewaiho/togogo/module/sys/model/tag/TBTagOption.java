package com.leewaiho.togogo.module.sys.model.tag;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.leewaiho.togogo.common.base.model.BaseModel;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/20
 * Project togogo-shixun
 */
@Entity
@Table(name = "T_B_TAG_OPTION")
public class TBTagOption extends BaseModel implements Serializable {
    
    private String name;
    
    private String value;
    
    private String type;
    
    private boolean defaultOption;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TAG_ID", referencedColumnName = "ID")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private TBTag tag;
    
    public TBTagOption() {
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
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public boolean isDefaultOption() {
        return defaultOption;
    }
    
    public void setDefaultOption(boolean defaultOption) {
        this.defaultOption = defaultOption;
    }
    
    public TBTag getTag() {
        return tag;
    }
    
    public void setTag(TBTag tag) {
        this.tag = tag;
    }
    
    public class Type {
        public static final String ADD_POINT = "addPoint";
        public static final String COEFFICIENT = "coefficient";
    }
}
