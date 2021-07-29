package cn.wjzhang.shopcommon.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//订单
@Entity(name = "shop_order")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;//订单id
    private Integer uid;//用户id
    private String username;//用户名
}