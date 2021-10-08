package com.lzp.service;

import com.lzp.entity.BookEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    // 显示所有数据
    public List<BookEntity> findallbook();

    // 得到某本书的细节
    public BookEntity findBookById(int bookid);

    /*搜索推荐的书*/
    public List<BookEntity> findBookrecommend();

    /*按名字查询书籍*/
    public List<BookEntity> findBookBybookname(String bookname);

    /*设置书籍推荐*/
    public void setBookrecommend(String recommend,int bookid);

    /*添加新的书籍*/
    public void addnewbook(String bookname,String author,String press, String pressdate, String briefintro,String price);

    /*添加新的书籍*/
    public int updatebook(int bookid,String bookname,String author,String press, String pressdate, String briefintro,String price);

    /*删除图书*/
    public int delbook(int bookid);

    /*更新图书封面*/
    public int updateBookprc(int bookid);
}
