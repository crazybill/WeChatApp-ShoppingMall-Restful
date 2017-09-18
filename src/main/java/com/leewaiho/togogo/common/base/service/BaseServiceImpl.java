package com.leewaiho.togogo.common.base.service;

import com.leewaiho.togogo.common.base.model.BaseModel;
import com.leewaiho.togogo.common.base.repository.BaseRepository;
import com.leewaiho.togogo.common.util.IdWorker;
import com.leewaiho.togogo.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/18
 * Project togogo-shixun
 */
public class BaseServiceImpl<T extends BaseModel, D extends Serializable> implements BaseService<T, D> {
    
    protected final Logger log = LoggerFactory.getLogger(getClass().getName());
    
    @Autowired
    private BaseRepository<T, D> repository;
    
    @Override
    public List<T> findAll() {
        return repository.findAll();
    }
    
    @Override
    public T save(T t) {
        String id = t.getId();
        String className = t.getClass().getSimpleName();
        if (StringUtils.isEmpty(id)) {
            id = String.valueOf(IdWorker.getFlowIdWorkerInstance().nextId());
            t.setId(id);
            log.info("新增操作 ID: {}, Bean: {}", id, className);
        } else {
            log.info("更新操作 ID: {}, Bean: {}", id, className);
        }
        t.setUpdateTime(new Date());
        return repository.save(t);
    }
}
