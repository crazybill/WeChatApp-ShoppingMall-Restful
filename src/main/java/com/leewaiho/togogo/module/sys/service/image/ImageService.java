package com.leewaiho.togogo.module.sys.service.image;

import com.leewaiho.togogo.common.base.service.BaseService;
import com.leewaiho.togogo.module.sys.model.image.TSImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/20
 * Project togogo-shixun
 */

public interface ImageService extends BaseService<TSImage> {
    
    Page<TSImage> findAllByType(String type, Pageable pageable);
    
    TSImage findByUrl(String url);
    
}
