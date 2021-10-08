package com.lzp.entity;

import lombok.Data;

@Data
public class CommentEntity {

    /*评论编号*/
    private int commentid;
    /*评论用户id*/
    private int userid;
    /*用户评论分数*/
    private int score;
    /*评论的类别*/
    private int type;
    /*评论内容*/
    private String comment;
    /*类别的编号*/
    private int typeid;
    /*评论日期*/
    private String commentdate;

}
