package com.leewaiho.togogo.module.sys.repository.product;

import com.leewaiho.togogo.common.base.repository.BaseRepository;
import com.leewaiho.togogo.module.sys.model.product.TBProduct;
import com.leewaiho.togogo.module.sys.model.tag.TBTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/20
 * Project togogo-shixun
 */
@Repository
public interface ProductRepository extends BaseRepository<TBProduct> {
    
    Page<TBProduct> findAllByProductTagsContains(TBTag tbTag, Pageable pageable);
    
}
