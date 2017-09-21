package com.leewaiho.togogo.module.sys.model.tag;

import com.leewaiho.togogo.common.base.model.BaseModel;

import javax.persistence.Entity;
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
    
    private boolean defaultOption;
    
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
    
    @Override
    public String toString() {
        return "TBTagOption{" +
                       "name='" + name + '\'' +
                       ", value='" + value + '\'' +
                       ", type='" + type + '\'' +
                       ", defaultOption=" + defaultOption +
                       "} " + super.toString();
    }
    
    public class Type {
        public static final String ADD_POINT = "addPoint";
        public static final String COEFFICIENT = "coefficient";
    }
}
