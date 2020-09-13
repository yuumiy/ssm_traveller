package com.zhong.ssm.service;

import com.zhong.ssm.domain.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAll(Integer page,Integer size);

    public void save(Product product);

    void deleteById(Integer pid);

    Product findById(Integer pid);

    void edit(Product product);
}
