package com.huipengpay.demo;

import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.Map;

/**
 * 临时内存中的订单仓库
 */
public class Repository {
    private static Map<String, Order> datasource = Maps.newHashMap();

    public static void save(Order order) {
        datasource.put(order.getOrderNumber(), order);
    }

    public static Order find(String id) {
        return datasource.get(id);
    }

    public static Collection<Order> findAll() {
        return datasource.values();
    }

}
