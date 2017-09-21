package com.leewaiho.togogo.module.sys.service.impl;

import com.leewaiho.togogo.common.base.service.BaseServiceImpl;
import com.leewaiho.togogo.common.util.IdWorker;
import com.leewaiho.togogo.module.sys.model.tag.TBTag;
import com.leewaiho.togogo.module.sys.model.tag.TBTagOption;
import com.leewaiho.togogo.module.sys.repository.tag.TagOptionRepository;
import com.leewaiho.togogo.module.sys.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/20
 * Project togogo-shixun
 */
@Service
public class TagServiceImpl extends BaseServiceImpl<TBTag> implements TagService {
    
    @Autowired
    private TagOptionRepository tagOptionRepository;
    
    @Override
    public void create(TBTag tbTag) {
        super.create(tbTag);
        createOptionIfNotExist(tbTag);
    }
    
    @Override
    public void update(TBTag tbTag, String id) {
        super.update(tbTag, id);
        createOptionIfNotExist(tbTag);
    }
    
    private void createOptionIfNotExist(TBTag tbTag) {
        if (tbTag.getTagOptions() != null && tbTag.getTagOptions().size() != 0) {
            Set<TBTagOption> tagOptions = tbTag.getTagOptions();
            for (TBTagOption tagOption : tagOptions) {
                if (tagOption.getId() != null) continue;
                tagOption.setId(String.valueOf(IdWorker.getFlowIdWorkerInstance().nextId()));
                tagOptionRepository.save(tagOption);
            }
        }
    }
}
