package com.leewaiho.togogo.common.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/18
 * Project togogo-shixun
 */
@NoRepositoryBean
public interface BaseRepository<T, D extends Serializable> extends JpaRepository<T, D> {
}
