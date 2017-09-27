package com.leewaiho.togogo.module.sys.service.tag;

import com.leewaiho.togogo.common.base.service.BaseService;
import com.leewaiho.togogo.module.sys.model.tag.TBTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/20
 * Project togogo-shixun
 */
public interface TagService extends BaseService<TBTag> {
    
    Page<TBTag> findAllByType(String type, Pageable pageable);
    
    Page<TBTag> findTagByTypeAndProduct(String type, String productId, Pageable pageable);
}
