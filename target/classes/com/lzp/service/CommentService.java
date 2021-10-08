package com.lzp.service;

import com.lzp.entity.CommentEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    // 得到某本书的评论
    public List<CommentEntity> findBookComment(int bookid);

    // 得到某部电影的评论
    public List<CommentEntity> findMovieComment(int movieid);

    // 得到某音乐的评论
    public List<CommentEntity> findMusicComment(int musicid);

    // 提交用户评论
    public void submitcomment(String type, int uesrid, String comment,int id, String commentdate);
}
