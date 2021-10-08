package com.lzp.mapper;

import com.lzp.entity.BookEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {

    /*搜索所有的书籍*/
    public List<BookEntity> findallbook();

    /*按id查询书籍*/
    public BookEntity findBookById(@Param("bookid") int bookid);

    /*搜索推荐的书*/
    public List<BookEntity> findBookrecommend();

    /*按名字查询书籍*/
    public List<BookEntity> findBookBybookname(@Param("bookname") String bookname);

    /*设置书籍推荐*/
    public void setBookrecommend(@Param("recommend") String recommend,@Param("bookid") int bookid);

    /*添加新的书籍*/
    public void addnewbook(@Param("bookname") String bookname,@Param("author") String author,@Param("press") String press,
                           @Param("pressdate") String pressdate,@Param("briefintro") String briefintro,@Param("price") String price);

    /*更新书籍信息*/
    public int updatebook(@Param("bookid") int bookid,@Param("bookname") String bookname,@Param("author") String author,@Param("press") String press,
                          @Param("pressdate") String pressdate,@Param("briefintro") String briefintro,@Param("price") String price);

    /*删除图书*/
    public int delbook(@Param("bookid") int bookid);

    /*更新图书封面*/
    public int updateBookprc(@Param("bookid") int bookid);
}
