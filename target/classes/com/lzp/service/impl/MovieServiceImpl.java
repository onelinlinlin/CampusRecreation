package com.lzp.service.impl;

import com.lzp.entity.BookEntity;
import com.lzp.entity.MovieEntity;
import com.lzp.mapper.BookMapper;
import com.lzp.mapper.MovieMapper;
import com.lzp.service.BookService;
import com.lzp.service.MovieService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieMapper movieMapper;

    /*寻找所有电影*/
    @Override
    public List<MovieEntity> findallmovie() {
        return movieMapper.findallmovie();
    }

    /*查询某个id的电影信息*/
    @Override
    public MovieEntity findMovieById(int movieid) {
        return movieMapper.findMovieById(movieid);
    }

    /*搜索推荐的电影*/
    @Override
    public List<MovieEntity> findmovierecommend() {
        return movieMapper.findmovierecommend();
    }

    /*按名字查询电影*/
    @Override
    public List<MovieEntity> findMovieBymoviename(String moviename) {
        return movieMapper.findMovieBymoviename(moviename);
    }

    /*设置推荐电影*/
    @Override
    public void setMovierecommend(String recommend, int movieid) {
        movieMapper.setMovierecommend(recommend,movieid);
    }

    /*添加新的电影*/
    @Override
    public void addnewmovie(String moviename, String director, String language, String showtime, String duration, String actor, String briefintro) {
        movieMapper.addnewmovie(moviename,director,language,showtime,duration,actor,briefintro);
    }

    /*更新电影信息*/
    @Override
    public int updatemovie(int movieid, String moviename, String director, String language, String showtime, String duration, String actor, String briefintro) {
        return movieMapper.updatemovie(movieid,moviename,director,language,showtime,duration,actor,briefintro);
    }

    /*删除电影*/
    @Override
    public int delmovie(int movieid) {
        return movieMapper.delmovie(movieid);
    }

    /*更新电影封面*/
    @Override
    public int updateMovieprc(int movieid) {
        return movieMapper.updateMovieprc(movieid);
    }

}
