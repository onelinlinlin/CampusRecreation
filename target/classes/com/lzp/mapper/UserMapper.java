package com.lzp.mapper;

import com.lzp.entity.UserEntity;
import com.lzp.service.UserService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    /*检查登陆*/
    public UserEntity checkLogin(@Param("username") String username, @Param("password") String password);

    /*通过id查找所有信息*/
    public UserEntity finduserById(@Param("userid") int userid);

    /*通过id查找所有信息*/
    public String finduserByusername(@Param("username") String username);

    /*通过id查找所有信息*/
    public void addnewUser(@Param("username") String username, @Param("password") String password);

    /*更新用户头像信息*/
    public void updateUserprc(@Param("userid") int userid, @Param("headprc") String headprc);

    /*更新用户信息*/
    public void updateUserinfo(@Param("nickname") String nickname,@Param("phone") String phone,@Param("sex") String sex, @Param("userid") int userid);

    /*查找所有角色是用户的用户*/
    public List<UserEntity> findAllUserByRole();

    /*修改用户状态*/
    public int updateUserState(@Param("userid") int userid,@Param("state") String state);

    /*删除用户*/
    public int delUser(@Param("userid") int userid);
}
