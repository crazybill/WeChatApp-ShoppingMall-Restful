package com.leewaiho.togogo.common.base.service;

import com.leewaiho.togogo.common.base.model.BaseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;


/**
 * @Author leewaiho
 * @Email 791783391@qq.com
 * @Date 2017/9/15
 */
@Transactional
public interface BaseService<T extends BaseModel> {
    
    T findOne(String id);
    
    Page<T> findAll(Pageable pageable);
    
    T save(T t);
    
    void destroy(String id);
    
    T create(T t);
    
    T update(T t, String id);
    
    void delete(String id);
}
