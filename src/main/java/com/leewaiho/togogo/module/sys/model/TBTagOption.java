package com.leewaiho.togogo.module.sys.model;

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
    
    private String name;
    
    private String value;
    
    private String type;
    
    @ManyToOne
    @JoinColumn(name = "TAG_ID", referencedColumnName = "id")
    private TBTag tag;
    
    private boolean isDefault;
    
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
    
    public boolean isDefault() {
        return isDefault;
    }
    
    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
        
        
    }
    
    public TBTag getTag() {
        return tag;
    }
    
    public void setTag(TBTag tag) {
        this.tag = tag;
    }
    
    @Override
    public String toString() {
        return "TBTagOption{" +
                       "name='" + name + '\'' +
                       ", value='" + value + '\'' +
                       ", type='" + type + '\'' +
                       ", tag=" + tag +
                       ", isDefault=" + isDefault +
                       "} " + super.toString();
    }
}
