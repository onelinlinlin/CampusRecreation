package com.lzp.service;


import com.lzp.entity.MovieEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {

    // 显示所有数据
    public List<MovieEntity> findallmovie();

    // 得到某电影的细节
    public MovieEntity findMovieById(int movieid);

    /*搜索推荐的电影*/
    public List<MovieEntity> findmovierecommend();

    /*按名字查询电影*/
    public List<MovieEntity> findMovieBymoviename(String moviename);

    /*设置电影推荐*/
    public void setMovierecommend(String recommend,int movieid);

    /*添加新的电影*/
    public void addnewmovie(String moviename,String director,String language,String showtime,String duration,String actor,String briefintro);

    /*更新电影信息*/
    public int updatemovie(int movieid,String moviename,String director,String language, String showtime,String duration,String actor,String briefintro);

    /*删除电影*/
    public int delmovie(int movieid);

    /*更新电影封面*/
    public int updateMovieprc(int movieid);

}
