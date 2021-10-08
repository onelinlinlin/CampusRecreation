package com.lzp.service.impl;


import com.lzp.entity.UserEntity;
import com.lzp.mapper.UserMapper;
import com.lzp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserEntity checkLogin(String username, String password) {
        return userMapper.checkLogin(username, password);
    }

    /*搜索用户*/
    @Override
    public UserEntity finduserById(int userid) {
        return userMapper.finduserById(userid);
    }

    /*按用户名查找用户名*/
    @Override
    public String finduserByusername(String username) {
        return userMapper.finduserByusername(username);
    }

    /*添加新用户*/
    @Override
    public void addnewUser(String username, String password) {
        userMapper.addnewUser(username,password);
    }

    /*更新用户头像*/
    @Override
    public void updateUserprc(int userid, String headprc) {
        userMapper.updateUserprc(userid,headprc);
    }

    /*更新用户信息*/
    @Override
    public void updateUserinfo(String nickname, String phone, String sex,int userid) {
        userMapper.updateUserinfo(nickname, phone, sex, userid);
    }

    /*查找所有角色为用户的用户*/
    @Override
    public List<UserEntity> findAllUserByRole() {
        return userMapper.findAllUserByRole();
    }

    /*修改用户状态*/
    @Override
    public int updateUserState(int userid, String state) {
        return userMapper.updateUserState(userid,state);
    }

    /*删除用户*/
    @Override
    public int delUser(int userid) {
        return userMapper.delUser(userid);
    }

}

