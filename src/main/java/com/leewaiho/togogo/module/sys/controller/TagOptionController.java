package com.leewaiho.togogo.module.sys.controller;

import com.alibaba.druid.util.StringUtils;
import com.leewaiho.togogo.common.base.controller.BaseController;
import com.leewaiho.togogo.common.exception.ServiceException;
import com.leewaiho.togogo.common.pojo.Result;
import com.leewaiho.togogo.common.util.HttpContextUtil;
import com.leewaiho.togogo.module.sys.model.tag.TBTag;
import com.leewaiho.togogo.module.sys.model.tag.TBTagOption;
import com.leewaiho.togogo.module.sys.service.tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.leewaiho.togogo.common.base.controller.BaseController.BASE_PATH;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/23
 * Project togogo-shixun
 */
@RestController
@RequestMapping(BASE_PATH + "/tag/options")
public class TagOptionController extends BaseController<TBTagOption> {
    
    @Autowired
    private TagService tagService;
    
    @Override
    public Result listPage(Pageable pageable) {
        throw new ServiceException("查什么查,不准查");
    }
    
    @Override
    public Result<TBTagOption> save(@RequestBody TBTagOption tbTagOption) {
        if (StringUtils.isEmpty(tbTagOption.getId())) {
            String tagId = HttpContextUtil.getRequest().getParameter("tagId");
            if (StringUtils.isEmpty(tagId)) throw new ServiceException("所属标签不能为空");
            TBTag one = tagService.findOne(tagId);
            tbTagOption.setTag(one);
        }
        return super.save(tbTagOption);
    }
}
