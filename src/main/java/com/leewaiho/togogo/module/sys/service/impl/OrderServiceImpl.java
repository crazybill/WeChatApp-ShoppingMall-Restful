package com.leewaiho.togogo.module.sys.service.impl;

import com.leewaiho.togogo.common.base.service.BaseServiceImpl;
import com.leewaiho.togogo.module.sys.model.order.TBOrder;
import com.leewaiho.togogo.module.sys.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/21
 * Project togogo-shixun
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl<TBOrder> implements OrderService {
    
    @Override
    public TBOrder updateState(String id, int state) {
        TBOrder order = findById(id);
        order.setState(state);
        return save(order);
    }
}
