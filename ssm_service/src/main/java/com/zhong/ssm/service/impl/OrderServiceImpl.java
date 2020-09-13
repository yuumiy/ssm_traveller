package com.zhong.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.zhong.ssm.dao.MemberDao;
import com.zhong.ssm.dao.OrderDao;
import com.zhong.ssm.dao.ProductDao;
import com.zhong.ssm.domain.Member;
import com.zhong.ssm.domain.Order;
import com.zhong.ssm.domain.Product;
import com.zhong.ssm.domain.Traveller;
import com.zhong.ssm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private ProductDao productDao;

    @Override
    public List<Order> findAll(Integer page,Integer size) {

        //pageNum是当前页码值，pageSize是每页显示条数
        PageHelper.startPage(page,size);
        return orderDao.findAll();
    }

    @Override
    public Order findById(Integer orderId) {
        return orderDao.findById(orderId);
    }

    @Override
    public void deleteById(Integer oid) {
        orderDao.deleteOrder_TravellerById(oid);
        orderDao.deleteOrderById(oid);
    }

    @Override
    public List<Member> findAllMembers() {
        return memberDao.findAll();
    }

    @Override
    public List<Product> findAllProducts() {
        return productDao.findAll();
    }

    @Override
    public void save(Order order) {
        orderDao.save(order);
    }

    @Override
    public void edit(Order order) {
        orderDao.edit(order);
    }
}
