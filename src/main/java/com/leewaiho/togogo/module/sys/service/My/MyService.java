package com.leewaiho.togogo.module.sys.service.My;

import com.leewaiho.togogo.module.sys.model.order.TBOrder;
import com.leewaiho.togogo.module.sys.model.user.TSUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/10/9
 * Project togogo-shixun
 */
public interface MyService {
    Page<TBOrder> getMyOrders(TSUser user, Pageable pageable);
}
