package com.lzp.entity;

import lombok.Data;

@Data
public class ActivityEntity {

    /*活动id*/
    private int activityid;

    /*活动发起人*/
    private int userid;

    /*活动名称*/
    private String activityname;

    /*活动地点*/
    private String activityplace;

    /*活动简介*/
    private String activityintro;

    /*活动日期*/
    private String activitydate;

    /*活动时长*/
    private String activitytime;

    /*报名截至时间*/
    private String endtime;

    /*活动图片*/
    private String activityprc;

    /*活动状态*/
    private String state;
}
