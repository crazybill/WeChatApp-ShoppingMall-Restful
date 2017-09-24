package com.leewaiho.togogo.module.sys.controller;

import com.leewaiho.togogo.common.base.controller.BaseController;
import com.leewaiho.togogo.common.pojo.Result;
import com.leewaiho.togogo.module.sys.model.order.TBOrder;
import com.leewaiho.togogo.module.sys.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.leewaiho.togogo.common.base.controller.BaseController.BASE_PATH;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/21
 * Project togogo-shixun
 */
@RestController
@RequestMapping(BASE_PATH + "/orders")
public class OrderController extends BaseController<TBOrder> {
    
    @Autowired
    private OrderService orderService;
    
    @RequestMapping(value = "/state", method = RequestMethod.PUT)
    public Result payOrder(@RequestParam(value = "id") String id,
                           @RequestParam(value = "state") int state
    ) {
        return Result.success(orderService.updateState(id, state));
    }
    
    @RequestMapping(value = "/state", method = RequestMethod.GET)
    public Result getOrderStates() {
        List<Map> result = new ArrayList<>();
        for (TBOrder.State state : TBOrder.State.values()) {
            Map record = new LinkedHashMap<>();
            record.put("name", state.name());
            record.put("title", state.getTitle());
            record.put("value", state.getValue());
            result.add(record);
        }
        return Result.success(result);
    }
    
}
