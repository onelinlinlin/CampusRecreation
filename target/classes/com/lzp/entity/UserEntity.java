package com.lzp.entity;

import lombok.Data;

@Data
public class UserEntity {

    // 用户id
    private int userid;
    // 用户名称
    private String username;
    // 用户密码
    private String password;
    // 用户角色
    private String role;
    // 用户昵称
    private String nickname;
    // 用户性别
    private String sex;
    // 用户电话
    private String phone;
    // 用户头像
    private String headprc;
    // 用户状态
    private String state;

}
