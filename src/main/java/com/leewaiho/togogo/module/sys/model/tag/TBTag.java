package com.leewaiho.togogo.module.sys.model.tag;

import com.leewaiho.togogo.common.base.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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
    
    @OneToMany(mappedBy = "tag",fetch = FetchType.EAGER)
    private Set<TBTagOption> tagOptions;
    
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
    
    @Override
    public String toString() {
        return "TBTag{" +
                       "name='" + name + '\'' +
                       ", tagOptions=" + tagOptions +
                       ", type='" + type + '\'' +
                       "} " + super.toString();
    }
    
    public class Type{
        public static final String PRODUCT = "product";
    }
}
