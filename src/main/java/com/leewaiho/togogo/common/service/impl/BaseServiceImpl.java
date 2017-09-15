package com.leewaiho.togogo.common.service.impl;

import com.leewaiho.togogo.common.repository.BaseRepository;
import com.leewaiho.togogo.common.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * @Author leewaiho
 * @Email 791783391@qq.com
 * @Date 2017/9/15
 */
public class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {
    
    protected Logger log = LoggerFactory.getLogger(getClass().getName());
    
    @Autowired
    private BaseRepository<T, ID> baseRepository;
    
    @Override
    public Page<T> findAll(Pageable pageable) {
        return baseRepository.findAll(pageable);
    }
    
    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }
}
