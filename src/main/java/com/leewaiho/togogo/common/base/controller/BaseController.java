package com.leewaiho.togogo.common.base.controller;

import com.leewaiho.togogo.common.base.model.BaseModel;
import com.leewaiho.togogo.common.base.service.BaseService;
import com.leewaiho.togogo.common.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/20
 * Project togogo-shixun
 */
public abstract class BaseController<T extends BaseModel> {
    
    protected Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private BaseService<T> service;
    
    @RequestMapping(value = "/{id:\\d{18}}", method = RequestMethod.GET)
    public  Result findById(@PathVariable String id) {
        return Result.success(service.findOne(id));
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public Result listPage(@PageableDefault(size = 100, sort = {"sort", "id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return Result.success(service.findAll(pageable));
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public Result<T> save(@RequestBody T t) {
        return Result.success(service.save(t));
    }
    
    @RequestMapping(value = "/{id:\\d{18}}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        service.destroy(id);
        return Result.success(null);
    }
    
}
