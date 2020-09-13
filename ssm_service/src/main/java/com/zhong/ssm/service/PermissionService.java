package com.zhong.ssm.service;

import com.zhong.ssm.domain.Permission;

import java.util.List;

public interface PermissionService {

    public List<Permission> findAll(Integer page,Integer size) throws Exception;

    void save(Permission permission) throws Exception;

    Permission findById(Integer id) throws Exception;

    void deleteById(Integer id) throws Exception;
}
