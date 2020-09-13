package com.zhong.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.zhong.ssm.dao.PermissionDao;
import com.zhong.ssm.domain.Permission;
import com.zhong.ssm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PermissionServiceImpl implements PermissionService{

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public void deleteById(Integer id) throws Exception {
        permissionDao.deleteFromRole_Permission(id);
        permissionDao.deleteById(id);
    }

    @Override
    public Permission findById(Integer id) throws Exception {
        return permissionDao.findById(id);
    }

    @Override
    public void save(Permission permission) throws Exception{
        permissionDao.save(permission);
    }

    @Override
    public List<Permission> findAll(Integer page,Integer size) throws Exception{
        PageHelper.startPage(page,size);
        return permissionDao.findAll();
    }
}
