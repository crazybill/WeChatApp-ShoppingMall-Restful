package com.leewaiho.togogo.module.sys.service;

import com.leewaiho.togogo.common.base.service.BaseService;
import com.leewaiho.togogo.module.sys.model.order.TBOrder;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/21
 * Project togogo-shixun
 */
public interface OrderService extends BaseService<TBOrder> {
    
    /**
     * 更新订单的阶段
     * @param id
     * @param state
     * @return
     */
    TBOrder updateState(String id, int state);
    
}
