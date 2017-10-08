package com.leewaiho.togogo.module.sys.controller;

import com.leewaiho.togogo.common.base.controller.BaseController;
import com.leewaiho.togogo.common.exception.ServiceException;
import com.leewaiho.togogo.common.pojo.Result;
import com.leewaiho.togogo.common.util.StringUtils;
import com.leewaiho.togogo.module.sys.model.image.TSImage;
import com.leewaiho.togogo.module.sys.service.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.leewaiho.togogo.common.Const.BASE_PATH;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/20
 * Project togogo-shixun
 */
@RestController
@RequestMapping(BASE_PATH + "/images")
public class ImageController extends BaseController<TSImage> {
    
    @Autowired
    private ImageService imageService;
    
    @RequestMapping(method = RequestMethod.GET, params = "type")
    public Result findByType(@RequestParam(name = "type") String type, Pageable pageable) {
        if (StringUtils.isEmpty(type))
            throw new ServiceException("类型不能为空");
        return Result.success(imageService.findAllByType(type, new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), new Sort(Sort.Direction.DESC, "sort"))));
    }
    
}
