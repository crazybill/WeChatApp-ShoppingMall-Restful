package com.leewaiho.togogo.module.sys.model.image;

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
@Table(name = "T_S_IMAGE")
public class TSImage extends BaseModel implements Serializable {
    
    private String url;
    
    private int sort = 1;
    
    private String type;
    
    private String description;
    
    public TSImage() {
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public int getSort() {
        return sort;
    }
    
    public void setSort(int sort) {
        this.sort = sort;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return "TSImage{" +
                       "url='" + url + '\'' +
                       ", sort=" + sort +
                       ", type='" + type + '\'' +
                       ", description='" + description + '\'' +
                       "} " + super.toString();
    }
}
