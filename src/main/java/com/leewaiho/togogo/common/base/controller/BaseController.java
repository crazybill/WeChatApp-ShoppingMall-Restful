package com.leewaiho.togogo.common.base.controller;

import com.leewaiho.togogo.common.pojo.Result;
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
    
    public static final String BASE_PATH = "/api";
    
    @RequestMapping(value = "/{id:\\d{18}}", method = RequestMethod.GET)
    public abstract Result findById(@PathVariable D id);
    
    @RequestMapping(method = RequestMethod.GET)
    public abstract Result listPage(Pageable pageable);
    
    @RequestMapping(method = RequestMethod.POST)
    public abstract Result<T> save(T t);
    
    @RequestMapping(value = "/{id:\\d{18}}", method = RequestMethod.DELETE)
    public abstract Result delete(@PathVariable D id);
    
}
