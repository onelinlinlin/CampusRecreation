package com.lzp.mapper;

import com.lzp.entity.MusicEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MusicMapper {

    /*搜索所有的音乐*/
    public List<MusicEntity> findallmusic();

    /*按id查询音乐*/
    public MusicEntity findMusicById(@Param("musicid") int musicid);

    /*搜索推荐的音乐*/
    public List<MusicEntity> findmusicrecommend();

    /*按名字查询音乐*/
    public List<MusicEntity> findmusicBymusicname(@Param("musicname") String musicname);

    /*设置音乐推荐*/
    public void setMusicrecommend(@Param("recommend") String recommend,@Param("musicid") int musicid);

    /*添加新的音乐*/
    public void addnewmusic(@Param("musicname") String musicname,@Param("singer") String singer,@Param("publisher") String publisher,
                            @Param("briefintro") String briefintro,@Param("pubdate") String pubdate,@Param("musictype") String musictype);

    /*更新音乐信息*/
    public int updatemusic(@Param("musicid") int musicid,@Param("musicname") String musicname,@Param("singer") String singer,@Param("publisher") String publisher,
                          @Param("pubdate") String pubdate,@Param("briefintro") String briefintro);

    /*删除音乐*/
    public int delmusic(@Param("musicid") int musicid);

    /*更新音乐封面*/
    public int updateMusicprc(@Param("musicid") int musicid);
}
