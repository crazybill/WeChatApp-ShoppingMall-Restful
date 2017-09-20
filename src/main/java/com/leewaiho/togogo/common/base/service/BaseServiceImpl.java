package com.leewaiho.togogo.common.base.service;

import com.leewaiho.togogo.common.base.model.BaseModel;
import com.leewaiho.togogo.common.base.repository.BaseRepository;
import com.leewaiho.togogo.common.exception.ServiceException;
import com.leewaiho.togogo.common.util.IdWorker;
import com.leewaiho.togogo.common.util.MyBeanUtil;
import com.leewaiho.togogo.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
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
    public T findById(D id) {
        return repository.getOne(id);
    }
    
    @Override
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
    
    @Override
    public List<T> findAll() {
        return repository.findAll();
    }
    
    @Override
    public T save(T t) {
        try {
            String id = t.getId();
            String className = t.getClass().getSimpleName();
            if (StringUtils.isEmpty(id)) {
                id = String.valueOf(IdWorker.getFlowIdWorkerInstance().nextId());
                t.setId(id);
                log.info("新增操作 ID: {}, Bean: {}", id, className);
            } else {
                T dest = repository.getOne((D) id);
                log.info(dest.toString());
                MyBeanUtil.copyProperties(t, dest);
                t = dest;
                log.info(t.toString());
                log.info("更新操作 ID: {}, Bean: {} ", id, className);
            }
            t.setUpdateTime(new Date());
            return repository.save(t);
        } catch (EntityNotFoundException e) {
            throw new ServiceException(t.getClass().getSimpleName() + " ID: " + t.getId() + " Not Exist!");
        }
    }
    
    @Override
    public void delete(D id) {
        repository.delete(id);
        
    }
    
    @Override
    public void delete(T t) {
        repository.delete(t);
    }
    
    
}
