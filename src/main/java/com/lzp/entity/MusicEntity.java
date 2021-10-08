package com.lzp.entity;

import lombok.Data;

@Data
public class MusicEntity {

    /*音乐编号*/
    private int musicid;

    /*音乐名称*/
    private String musicname;

    /*歌手*/
    private String singer;

    /*出版者*/
    private String publisher;

    /*音乐类别*/
    private String musictype;

    /*音乐简介*/
    private String briefintro;

    /*音乐评分*/
    private int score;

    /*发行日期*/
    private String pubdate;

    /*音乐封面*/
    private String musicprc;

    // 是否被推荐
    private String recommend;

}
