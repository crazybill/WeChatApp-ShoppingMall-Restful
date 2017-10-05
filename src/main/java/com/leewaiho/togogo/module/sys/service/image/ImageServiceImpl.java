package com.leewaiho.togogo.module.sys.service.image;

import com.leewaiho.togogo.common.Const;
import com.leewaiho.togogo.common.base.service.BaseServiceImpl;
import com.leewaiho.togogo.common.util.CheckUtils;
import com.leewaiho.togogo.module.sys.model.image.TSImage;
import com.leewaiho.togogo.module.sys.repository.image.ImageRepository;
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
public class ImageServiceImpl extends BaseServiceImpl<TSImage> implements ImageService {
    
    @Autowired
    private ImageRepository imageRepository;
    
    @Override
    public Page<TSImage> findAllByType(String type, Pageable pageable) {
        return imageRepository.findAllByType(type, pageable);
    }
    
    public TSImage findByUrl(String url) {
        CheckUtils.check(imageRepository.findByUrl(url) != null, Const.ServiceCode.NOTFOUND, "查找无结果");
        return imageRepository.findByUrl(url);
    }
}
