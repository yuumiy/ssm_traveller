package com.zhong.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.zhong.ssm.dao.UserDao;
import com.zhong.ssm.domain.Role;
import com.zhong.ssm.domain.UserInfo;
import com.zhong.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void addRoleToUser(Integer userId, Integer[] roleIds) {

        for(Integer roleId:roleIds){
            userDao.addRoleToUser(userId,roleId);
        }
    }

    @Override
    public void deleteUserById(Integer uid) {
        userDao.deleteFromUsers_roleById(uid);
        userDao.deleteUserById(uid);
    }

    @Override
    public List<Role> findOtherRoles(Integer userId) {
        return userDao.findOtherRoles(userId);
    }

    @Override
    public UserInfo findById(Integer id) throws Exception{

        return  userDao.findById(id);
    }

    @Override
    public void save(UserInfo userInfo) throws Exception {
        //对密码进行加密处理
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public List<UserInfo> findAll(Integer page,Integer size) throws Exception {
        PageHelper.startPage(page,size);
        return userDao.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //处理自己的用户对象封装成UserDetails
        User user = new User(userInfo.getUsername(), userInfo.getPassword(), userInfo.getStatus() == 0 ? false : true, true, true, true, getAuthority(userInfo.getRoles()));
        return user;
    }

    //作用就是返回一个List集合，集合中装入的是角色描述
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {

        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }
}
