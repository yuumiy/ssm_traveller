package com.zhong.ssm.service;

import com.zhong.ssm.domain.Role;
import com.zhong.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserInfo> findAll(Integer page,Integer size) throws Exception;

    void save(UserInfo userInfo) throws Exception;

    UserInfo findById(Integer id) throws Exception;

    List<Role> findOtherRoles(Integer userId) throws Exception;

    void addRoleToUser(Integer userId, Integer[] roleIds);

    void deleteUserById(Integer uid);
}
