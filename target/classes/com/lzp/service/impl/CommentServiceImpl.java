package com.lzp.service.impl;

import com.lzp.entity.CommentEntity;
import com.lzp.mapper.CommentMapper;
import com.lzp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    /*寻找某一本书的所有评论*/
    @Override
    public List<CommentEntity> findBookComment(int bookid) {
        return commentMapper.findallbookcomment(bookid);
    }

    /*寻找一部电影的所有评论*/
    @Override
    public List<CommentEntity> findMovieComment(int movieid) {
        return commentMapper.findallmoviecomment(movieid);
    }

    /*寻找一首音乐的所有评论*/
    @Override
    public List<CommentEntity> findMusicComment(int musicid) {
        return commentMapper.findallmusiccomment(musicid);
    }

    @Override
    public void submitcomment(String type, int uesrid, String comment, int id, String commentdate) {
        commentMapper.submitcomment(type, uesrid, comment, id, commentdate);
    }


}
