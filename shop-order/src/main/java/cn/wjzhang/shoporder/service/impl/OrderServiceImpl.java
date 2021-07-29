package cn.wjzhang.shoporder.service.impl;

import cn.wjzhang.shopcommon.entity.Order;
import cn.wjzhang.shoporder.dao.OrderDao;
import cn.wjzhang.shoporder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Override
    public void save(Order order) {
        orderDao.save(order);
    }
}
