package com.leewaiho.togogo.common.base.service;

import com.leewaiho.togogo.common.base.model.BaseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author leewaiho
 * @Email 791783391@qq.com
 * @Date 2017/9/15
 */
public interface BaseService<T extends BaseModel> {
    
    T findById(String id);
    
    Page<T> findAll(Pageable pageable);
    
    List<T> findAll();
    
    T save(T t);
    
    void delete(String id);
    
    void delete(T t);
    
}
