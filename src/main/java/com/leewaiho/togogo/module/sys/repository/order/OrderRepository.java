package com.leewaiho.togogo.module.sys.repository.order;

import com.leewaiho.togogo.common.base.repository.BaseRepository;
import com.leewaiho.togogo.module.sys.model.order.TBOrder;
import com.leewaiho.togogo.module.sys.model.user.TSUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/21
 * Project togogo-shixun
 */
@Repository
public interface OrderRepository extends BaseRepository<TBOrder> {
    
    Page<TBOrder> findAllByOwner(TSUser user, Pageable pageable);
}
