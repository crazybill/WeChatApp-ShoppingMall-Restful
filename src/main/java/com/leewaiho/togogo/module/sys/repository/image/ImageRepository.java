package com.leewaiho.togogo.module.sys.repository.image;

import com.leewaiho.togogo.common.base.repository.BaseRepository;
import com.leewaiho.togogo.module.sys.model.image.TSImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/20
 * Project togogo-shixun
 */
@Repository
public interface ImageRepository extends BaseRepository<TSImage> {
    
    Page<TSImage> findAllByType(String type, Pageable pageable);
    
}
