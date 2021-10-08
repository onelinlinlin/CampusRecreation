package com.lzp.service;

import com.lzp.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    // 检查登陆
    public UserEntity checkLogin(String username,String password);

    // 通过userid查找用户
    public UserEntity finduserById(int userid);

    // 通过用户名查找用户
    public String finduserByusername(String username);

    // 添加新的用户
    public void addnewUser(String username,String password);

    /*更新用户头像信息*/
    public void updateUserprc(int userid, String headprc);

    /*更新用户信息*/
    public void updateUserinfo(String nickname,String phone, String sex,int userid);

    /*查找所有角色是用户的用户*/
    public List<UserEntity> findAllUserByRole();

    /*修改用户状态*/
    public int updateUserState(int userid,String state);

    /*删除用户*/
    public int delUser(int userid);
}
