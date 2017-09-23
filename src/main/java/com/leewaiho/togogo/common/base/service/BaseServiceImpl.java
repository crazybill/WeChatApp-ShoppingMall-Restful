package com.leewaiho.togogo.common.base.service;

import com.leewaiho.togogo.common.base.model.BaseModel;
import com.leewaiho.togogo.common.base.repository.BaseRepository;
import com.leewaiho.togogo.common.exception.ServiceException;
import com.leewaiho.togogo.common.util.IdWorker;
import com.leewaiho.togogo.common.util.MyBeanUtil;
import com.leewaiho.togogo.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/18
 * Project togogo-shixun
 */
public abstract class BaseServiceImpl<T extends BaseModel> implements BaseService<T> {
    
    protected final Logger log = LoggerFactory.getLogger(getClass().getName());
    
    @Autowired
    protected BaseRepository<T> repository;
    
    @Override
    public T findOne(String id) {
        return repository.findOne(id);
    }
    
    @Override
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
    
    @Override
    public T save(T t) {
        try {
            String id = t.getId();
            String className = t.getClass().getSimpleName();
            String operationType;
            if (StringUtils.isEmpty(id)) {
                t = create(t);
                t.setCreateTime(new Date());
                operationType = "新增";
            } else {
                t = update(t, id);
                t.setUpdateTime(new Date());
                operationType = "更新";
            }
            t.setUpdateTime(new Date());
            log.info("=================" + operationType + "操作======================");
            log.info("{} : {}", className, t);
            log.info("=================" + operationType + "操作======================");
            return repository.save(t);
        } catch (EntityNotFoundException e) {
            throw new ServiceException(t.getClass().getSimpleName() + " ID: " + t.getId() + " Not Exist!");
        }
    }
    
    @Override
    public void delete(String id) {
        repository.delete(id);
    }
    
    public T create(T t) {
        t.setId(String.valueOf(IdWorker.getFlowIdWorkerInstance().nextId()));
        return t;
    }
    
    public T update(T t, String id) {
        T dest = findOne(id);
        if (dest == null)
            throw new ServiceException(t.getClass().getSimpleName() + " ID: " + t.getId() + " Not Exist!");
        MyBeanUtil.copyProperties(t, dest, true);
        return t;
    }
}
