package com.leewaiho.togogo.common.base.service;

import java.io.Serializable;
import java.util.List;

/**
 * @Author leewaiho
 * @Email 791783391@qq.com
 * @Date 2017/9/15
 */
public interface BaseService<T, ID extends Serializable> {
    
    List<T> findAll();
    
}
