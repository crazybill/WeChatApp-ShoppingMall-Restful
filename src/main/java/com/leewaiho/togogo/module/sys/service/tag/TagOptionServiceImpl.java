package com.leewaiho.togogo.module.sys.service.tag;

import com.leewaiho.togogo.common.base.service.BaseServiceImpl;
import com.leewaiho.togogo.common.exception.ServiceException;
import com.leewaiho.togogo.module.sys.model.tag.TBTagOption;
import org.springframework.stereotype.Service;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/22
 * Project togogo-shixun
 */
@Service
public class TagOptionServiceImpl extends BaseServiceImpl<TBTagOption> implements TagOptionService {
    @Override
    public TBTagOption save(TBTagOption tbTagOption) {
        if(tbTagOption.getTag() == null) throw new ServiceException("所属标签不能为空");
        return super.save(tbTagOption);
    }
}
