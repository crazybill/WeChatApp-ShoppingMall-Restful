package com.leewaiho.togogo.common.base.controller;

import com.leewaiho.togogo.common.base.service.BaseService;
import com.leewaiho.togogo.common.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/20
 * Project togogo-shixun
 */
public abstract class BaseController<T, D extends Serializable> {
    
    @Autowired
    private BaseService<T, D> service;
    
    public static final String BASE_PATH = "/api";
    
    @RequestMapping(value = "/{id:\\d{18}}", method = RequestMethod.GET)
    public  Result findById(@PathVariable D id) {
        return Result.success(service.findById(id));
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public Result listPage(Pageable pageable) {
        return Result.success(service.findAll(pageable));
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public Result<T> save(T t) {
        return Result.success(service.save(t));
    }
    
    @RequestMapping(value = "/{id:\\d{18}}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable D id) {
        service.delete(id);
        return Result.success(null);
    }
    
}
