package com.zhong.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.zhong.ssm.dao.ProductDao;
import com.zhong.ssm.domain.Product;
import com.zhong.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAll(Integer page,Integer size) {
        PageHelper.startPage(page,size);
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public void deleteById(Integer pid) {
        productDao.deleteById(pid);
    }

    @Override
    public Product findById(Integer pid) {
        return productDao.findById(pid);
    }

    @Override
    public void edit(Product product) {
        productDao.edit(product);
    }
}
