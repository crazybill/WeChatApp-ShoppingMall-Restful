package com.leewaiho.togogo.common.base.service;

import com.leewaiho.togogo.common.base.model.BaseModel;
import com.leewaiho.togogo.common.base.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/18
 * Project togogo-shixun
 */
public class BaseServiceImpl<T extends BaseModel, D extends Serializable> implements BaseService<T, D> {
    
    @Autowired
    private BaseRepository<T, D> repository;
    
    @Override
    public List<T> findAll() {
        return repository.findAll();
    }
}
