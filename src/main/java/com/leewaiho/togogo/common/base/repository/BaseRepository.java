package com.leewaiho.togogo.common.base.repository;

import com.leewaiho.togogo.common.base.model.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/18
 * Project togogo-shixun
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseModel> extends JpaRepository<T, String> {
}
