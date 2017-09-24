package com.leewaiho.togogo.module.sys.service.order;

import com.alibaba.druid.util.StringUtils;
import com.leewaiho.togogo.common.base.service.BaseServiceImpl;
import com.leewaiho.togogo.common.exception.ServiceException;
import com.leewaiho.togogo.module.sys.model.order.TBOrder;
import com.leewaiho.togogo.module.sys.model.order.TBOrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/21
 * Project togogo-shixun
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl<TBOrder> implements OrderService {
    
    @Autowired
    private OrderItemService orderItemService;
    
    @Override
    public TBOrder create(TBOrder tbOrder) {
        super.create(tbOrder);
        return establishRelation(tbOrder);
    }
    
    @Override
    public TBOrder update(TBOrder tbOrder, String id) {
        super.update(tbOrder, id);
        return establishRelation(tbOrder);
    }
    
    private TBOrder establishRelation(TBOrder order) {
        
        Set<TBOrderItem> orderItems = order.getOrderItems();
        
        if (orderItems == null || orderItems.size() == 0)
            throw new ServiceException("禁止保存空订单,请检查");
    
    
        try {
            findOne(order.getId());
        } catch (ServiceException e) {
            order.setOrderItems(null);
            try {
                order = repository.save(order);
            } catch (Exception ex) {
                log.error(e.getMessage());
                throw new ServiceException("初始化订单失败"); // 初始化父项失败时退出
            }
        }
        
        Set<TBOrderItem> items = new HashSet<>();
        for (TBOrderItem orderItem : orderItems) {
            if (StringUtils.isEmpty(orderItem.getId())) {
                orderItem.setOrder(order);
            } else {
                if (orderItem.getOrder() != null && orderItem.getOrder().getId() != order.getId())
                    throw new ServiceException("禁止复用订单子项");
            }
            
            TBOrderItem item = orderItemService.save(orderItem);
            items.add(item);
        }
        order.setOrderItems(items);
        return order;
    }
    
    @Override
    public TBOrder updateState(String id, int state) {
        TBOrder order = findOne(id);
        order.setState(state);
        return save(order);
    }
}
