package com.leewaiho.togogo.module.sys.service.product;

import com.leewaiho.togogo.common.base.service.BaseServiceImpl;
import com.leewaiho.togogo.module.sys.model.product.TBProduct;
import com.leewaiho.togogo.module.sys.model.tag.TBTag;
import com.leewaiho.togogo.module.sys.repository.product.ProductRepository;
import com.leewaiho.togogo.module.sys.service.tag.TagService;
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
public class ProductServiceImpl extends BaseServiceImpl<TBProduct> implements ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TagService tagService;
    
    @Override
    public Page<TBProduct> findProductsByTag(String tagId, Pageable pageable) {
        TBTag tag = tagService.findOne(tagId);
        return productRepository.findAllByProductTagsContains(tag, pageable);
    }
}
