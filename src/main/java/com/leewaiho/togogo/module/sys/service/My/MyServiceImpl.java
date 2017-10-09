package com.leewaiho.togogo.module.sys.service.My;

import com.leewaiho.togogo.module.sys.model.order.TBOrder;
import com.leewaiho.togogo.module.sys.model.user.TSUser;
import com.leewaiho.togogo.module.sys.repository.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/10/9
 * Project togogo-shixun
 */
@Service
public class MyServiceImpl implements MyService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Override
    public Page<TBOrder> getMyOrders(TSUser user, Pageable pageable) {
        return orderRepository.findAllByOwner(user, pageable);
    }
}
