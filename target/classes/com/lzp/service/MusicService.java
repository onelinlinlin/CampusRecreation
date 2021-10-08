package com.lzp.service;


import com.lzp.entity.MusicEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MusicService {

    // 显示所有数据
    public List<MusicEntity> findallmusic();

    // 得到某音乐的细节
    public MusicEntity findmusicById(int musicid);

    /*搜索推荐的音乐*/
    public List<MusicEntity> findmusicrecommend();

    /*按名字查询音乐*/
    public List<MusicEntity> findmusicBymusicname(String musicname);

    /*设置音乐推荐*/
    public void setMusicrecommend(String recommend, int musicid);

    /*添加新的音乐*/
    public void addnewmusic(String musicname,String singer,String publisher,String briefintro,String pubdate,String musictype);

    /*更新音乐信息*/
    public int updatemusic(int musicid,String musicname,String singer,String publisher, String pubdate,String briefintro);

    /*删除音乐*/
    public int delmusic(int musicid);

    /*更新音乐封面*/
    public int updateMusicprc(int musicid);

}
