package com.leewaiho.togogo.module.sys.model.tag;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leewaiho.togogo.common.base.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
    
    private String optionName;
    
    private String value;
    
    private String type;
    
    private boolean defaultOption;
    
    @ManyToOne
    @JoinColumn(name = "TAG_ID", referencedColumnName = "ID")
    @JsonIgnore
    private TBTag tag;
    
    public TBTagOption() {
    }
    
    public String getOptionName() {
        return optionName;
    }
    
    public void setOptionName(String optionName) {
        this.optionName = optionName;
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
