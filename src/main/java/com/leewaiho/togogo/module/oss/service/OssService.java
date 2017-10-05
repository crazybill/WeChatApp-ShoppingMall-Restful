package com.leewaiho.togogo.module.oss.service;

import com.leewaiho.togogo.module.oss.pojo.CallbackBody;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/10/4
 * Project togogo-shixun
 */
public interface OssService {
    
    String getToken();
    
    Object callback(CallbackBody callbackBody);
    
}
