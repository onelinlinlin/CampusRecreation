package com.lzp.entity;

import lombok.Data;

@Data
public class ScoreEntity {

    /*评分id*/
    private int scoreid;

    /*评分*/
    private int scores;

    /*用户id*/
    private int userid;

    /*类型*/
    private String type;

    /*类型id*/
    private int typeid;


}
