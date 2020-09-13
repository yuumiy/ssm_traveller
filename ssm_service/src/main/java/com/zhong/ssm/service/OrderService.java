package com.zhong.ssm.service;

import com.zhong.ssm.domain.Member;
import com.zhong.ssm.domain.Order;
import com.zhong.ssm.domain.Product;


import java.util.List;

public interface OrderService {
    List<Order> findAll(Integer page,Integer size);
    Order findById(Integer orderId);

    void deleteById(Integer oid);

    List<Member> findAllMembers();

    List<Product> findAllProducts();

    void save(Order order);

    void edit(Order order);
}
