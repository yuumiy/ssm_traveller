package com.zhong.ssm.service;

import com.zhong.ssm.domain.Permission;
import com.zhong.ssm.domain.Role;

import java.util.List;

public interface RoleService {

    public List<Role> findAll(Integer page,Integer size) throws Exception;

    void save(Role role) throws Exception;

    Role findById(Integer roleId) throws  Exception;

    List<Permission> findOtherPermissions(Integer roleId) throws Exception;

    void addPermissionToRole(Integer roleId, Integer[] permissionIds) throws Exception;

    void deleteRoleById(Integer roleId) throws Exception;
}
