package com.leewaiho.togogo.module.sys.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leewaiho.togogo.common.base.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/23
 * Project togogo-shixun
 */
@Entity
@Table(name = "T_S_ROLE")
public class TSRole extends BaseModel implements Serializable {
    
    private String name;
    
    private String description;
    
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<TSUser> users;
    
    public TSRole() {
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Set<TSUser> getUsers() {
        return users;
    }
    
    public void setUsers(Set<TSUser> users) {
        this.users = users;
    }
}
