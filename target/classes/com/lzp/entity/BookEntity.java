package com.lzp.entity;

import lombok.Data;

@Data
public class BookEntity {

    // 书籍id
    private int bookid;
    // 书籍名称
    private String bookname;
    // 书籍作者
    private String author;
    // 出版社
    private String press;
    // 出版日期
    private String pressdate;
    // 简介
    private String briefintro;
    // 书籍价格
    private String price;
    // 书籍封面
    private String bookprc;
    // 书籍类型
    private String booktype;
    // 书籍评分
    private String score;
    // 是否被推荐
    private String recommend;

}
