package com.leewaiho.togogo.module.sys.service.tag;

import com.alibaba.druid.util.StringUtils;
import com.leewaiho.togogo.common.base.service.BaseServiceImpl;
import com.leewaiho.togogo.common.exception.ServiceException;
import com.leewaiho.togogo.module.sys.model.tag.TBTag;
import com.leewaiho.togogo.module.sys.model.tag.TBTagOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/20
 * Project togogo-shixun
 */
@Service
public class TagServiceImpl extends BaseServiceImpl<TBTag> implements TagService {
    
    private static final List<String> NOT_NULL_TAGS = Arrays.asList(new String[]{"product"});
    @Autowired
    private TagOptionService tagOptionService;
    
    @Override
    public TBTag create(TBTag tbTag) {
        super.create(tbTag);
        return establishRelation(tbTag);
    }
    
    @Override
    public TBTag update(TBTag tbTag, String id) {
        super.update(tbTag, id);
        return establishRelation(tbTag);
    }
    
    private TBTag establishRelation(TBTag tag) {
        
        Set<TBTagOption> tagOptions = tag.getTagOptions();
    
        if ((tagOptions == null || tagOptions.size() == 0)) {
            if (NOT_NULL_TAGS.contains(tag.getType()))
                throw new ServiceException("禁止保存空标签,请检查"); // 子项为空时退出
            return repository.save(tag);
        }
    
        try {
            findOne(tag.getId());
        } catch (ServiceException e) {
            tag.setTagOptions(null);
            try {
                tag = repository.save(tag);
            } catch (Exception ex) {
                log.error(e.getMessage());
                throw new ServiceException("初始化标签失败"); // 初始化父项失败时退出
            }
        }
    
    
        Set<TBTagOption> options = new HashSet<>();
        for (TBTagOption tagOption : tagOptions) {
            if (StringUtils.isEmpty(tagOption.getId())) {
                tagOption.setTag(tag);
            } else {
                if (tagOption.getTag() != null && tagOption.getTag().getId() != tag.getId())
                    throw new ServiceException("禁止复用标签选项"); // 复用子项时退出
            }
            
            TBTagOption option = tagOptionService.save(tagOption);
            options.add(option);
        }
        tag.setTagOptions(options);
        return tag;
    }
}
