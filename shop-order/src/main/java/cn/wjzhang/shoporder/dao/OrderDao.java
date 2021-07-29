package cn.wjzhang.shoporder.dao;

import cn.wjzhang.shopcommon.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order,Long> {
}