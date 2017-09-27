package com.leewaiho.togogo.module.sys.controller;

import com.leewaiho.togogo.common.base.controller.BaseController;
import com.leewaiho.togogo.common.exception.ServiceException;
import com.leewaiho.togogo.common.pojo.Result;
import com.leewaiho.togogo.common.util.StringUtils;
import com.leewaiho.togogo.module.sys.model.tag.TBTag;
import com.leewaiho.togogo.module.sys.service.tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import static com.leewaiho.togogo.common.base.controller.BaseController.BASE_PATH;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/20
 * Project togogo-shixun
 */
@RestController
@RequestMapping(value = BASE_PATH + "/tags")
public class TagController extends BaseController<TBTag> {
    
    @Autowired
    private TagService tagService;
    
    @RequestMapping(method = RequestMethod.GET, params = "type")
    public Result findByType(@RequestParam(name = "type", required = true) String type, @PageableDefault(sort = {"sort", "id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        if (StringUtils.isEmpty(type))
            throw new ServiceException("类型不能为空");
        return Result.success(tagService.findAllByType(type, pageable));
    }
    
    
    @RequestMapping(method = RequestMethod.GET, value = "/{productId:\\d{18}}/{type}")
    public Result findAllByTypeAndProduct(@PathVariable("type") String type, @PathVariable("productId") String productId,@PageableDefault(sort = {"sort", "id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return Result.success(tagService.findTagByTypeAndProduct(type, productId, pageable));
    }
}
