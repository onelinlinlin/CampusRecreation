package com.lzp.entity;

import lombok.Data;

@Data
public class MovieEntity {

    /*电影编号*/
    private int movieid;

    /*电影名称*/
    private String moviename;

    /*电影导演*/
    private String director;

    /*电影语言*/
    private String language;

    /*电影上映时间*/
    private String showtime;

    /*电影时长*/
    private String duration;

    /*电影演员*/
    private String actor;

    /*电影简介*/
    private String briefintro;

    /*电影海报*/
    private String movieprc;

    /*电影类型*/
    private String movietype;

    /*电影评分*/
    private int score;

    // 是否被推荐
    private String recommend;

}
