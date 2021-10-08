package com.lzp.mapper;

import com.lzp.entity.BookEntity;
import com.lzp.entity.MovieEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MovieMapper {

    /*搜索所有的电影*/
    public List<MovieEntity> findallmovie();

    /*按id查询书籍*/
    public MovieEntity findMovieById(@Param("movieid") int movieid);

    /*搜索推荐的电影*/
    public List<MovieEntity> findmovierecommend();

    /*按名字查询电影*/
    public List<MovieEntity> findMovieBymoviename(@Param("moviename") String moviename);

    /*设置电影推荐*/
    public void setMovierecommend(@Param("recommend") String recommend,@Param("movieid") int movieid);

    /*添加新的电影*/
    public void addnewmovie(@Param("moviename") String moviename,@Param("director") String director,@Param("language") String language,
                            @Param("showtime") String showtime,@Param("duration") String duration,@Param("actor") String actor,@Param("briefintro") String briefintro);

    /*更新电影信息*/
    public int updatemovie(@Param("movieid") int movieid,@Param("moviename") String moviename,@Param("director") String director,@Param("language") String language,
                          @Param("showtime") String showtime,@Param("duration") String duration,@Param("actor") String actor,@Param("briefintro") String briefintro);

    /*删除电影*/
    public int delmovie(@Param("movieid") int movieid);

    /*更新电影封面*/
    public int updateMovieprc(@Param("movieid") int movieid);
}
