package com.leewaiho.togogo.module.sys.service.tag;

import com.leewaiho.togogo.common.base.service.BaseServiceImpl;
import com.leewaiho.togogo.module.sys.model.tag.TBTag;
import com.leewaiho.togogo.module.sys.repository.tag.TagRepository;
import com.leewaiho.togogo.module.sys.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/20
 * Project togogo-shixun
 */
@Service
public class TagServiceImpl extends BaseServiceImpl<TBTag> implements TagService {
    
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private ProductService productService;
    
    @Override
    public Page<TBTag> findAllByType(String type, Pageable pageable) {
        return tagRepository.findAllByTypeContains(type, pageable);
    }
    @Override
    public Page<TBTag> findTagByTypeAndProduct(String type, String productId, Pageable pageable) {
        return tagRepository.findAllByTypeAndProductsContains(type, productService.findOne(productId), pageable);
    }
}
