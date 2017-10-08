package com.leewaiho.togogo.module.sys.controller;

import com.leewaiho.togogo.common.base.controller.BaseController;
import com.leewaiho.togogo.common.pojo.Result;
import com.leewaiho.togogo.module.sys.model.product.TBProduct;
import com.leewaiho.togogo.module.sys.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.leewaiho.togogo.common.Const.BASE_PATH;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/20
 * Project togogo-shixun
 */
@RestController
@RequestMapping(BASE_PATH + "/products")
public class ProductController extends BaseController<TBProduct> {
    
    @Autowired
    private ProductService productService;
    
    @RequestMapping(method = RequestMethod.GET, value = "/tag/{tagId}")
    public Result findProductsByTag(@PathVariable("tagId") String tagId, @PageableDefault(sort = {"sort", "id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return Result.success(productService.findProductsByTag(tagId, pageable));
    }
}
