package com.lzp.mapper;

import com.lzp.entity.CommentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface CommentMapper {

    /*通过typeid搜索书籍所有的评论*/
    public List<CommentEntity> findallbookcomment(@Param("typeid") int bookid);

    /*通过typeid搜索电影所有的评论*/
    public List<CommentEntity> findallmoviecomment(@Param("typeid") int movieid);

    /*通过typeid搜索音乐所有的评论*/
    public List<CommentEntity> findallmusiccomment(@Param("typeid") int musicid);

    /*提交用户的评论*/
    public void submitcomment(@Param("types") String type, @Param("userid") int uesrid,
                                  @Param("comment") String comment, @Param("typeid") int id, @Param("commentdate") String commentdate);

}
