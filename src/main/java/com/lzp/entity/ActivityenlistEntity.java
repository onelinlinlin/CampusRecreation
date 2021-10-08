package com.lzp.entity;

import lombok.Data;

@Data
public class ActivityenlistEntity {

    /*活动报名id*/
    private int enlistid;

    /*活动id*/
    private int activityid;

    /*报名用户id*/
    private int userid;

    /*活动后随笔*/
    private String essay;

    /*报名者电话*/
    private String phone;

    /*报名者联系名*/
    private String name;

}
