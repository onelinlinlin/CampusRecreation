package com.lzp.service.impl;

import com.lzp.entity.BookEntity;
import com.lzp.mapper.BookMapper;
import com.lzp.service.BookService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    /*寻找所有书籍*/
    @Override
    public List<BookEntity> findallbook() {
        return bookMapper.findallbook();
    }

    /*查询某个id的书信息*/
    @Override
    public BookEntity findBookById(int bookid) {
        return bookMapper.findBookById(bookid);
    }

    @Override
    public List<BookEntity> findBookrecommend() {
        return bookMapper.findBookrecommend();
    }

    /*按名字查询书籍*/
    @Override
    public List<BookEntity> findBookBybookname(String bookname) {
        return bookMapper.findBookBybookname(bookname);
    }

    /*设置书籍推荐*/
    @Override
    public void setBookrecommend(String recommend,int bookid) {
        bookMapper.setBookrecommend(recommend,bookid);
    }

    /*添加新的图书*/
    @Override
    public void addnewbook(String bookname, String author, String press, String pressdate, String briefintro, String price) {
        bookMapper.addnewbook(bookname,author,press,pressdate,briefintro,price);
    }

    /*更新图书信息*/
    @Override
    public int updatebook(int bookid,String bookname, String author, String press, String pressdate, String briefintro, String price) {
        return bookMapper.updatebook(bookid,bookname,author,press,pressdate,briefintro,price);
    }

    /*删除图书*/
    @Override
    public int delbook(int bookid) {
        return bookMapper.delbook(bookid);
    }

    /*更新图书封面*/
    @Override
    public int updateBookprc(int bookid) {
        return bookMapper.updateBookprc(bookid);
    }

}
