package com.leewaiho.togogo.common.base;

import com.leewaiho.togogo.common.pojo.Result;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/20
 * Project togogo-shixun
 */
public abstract class BaseController<T, D extends Serializable> {
    
    public static final String BASE_PATH = "/api";
    
    public abstract Result findById(D id);
    
    public abstract Result listPage(Pageable pageable);
    
    public abstract Result<T> save(T t);
    
    public abstract Result delete(D id);
    
    
}
