package com.lzp.service.impl;

import com.lzp.entity.MovieEntity;

import com.lzp.entity.MusicEntity;
import com.lzp.mapper.MusicMapper;
import com.lzp.service.MusicService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    private MusicMapper musicMapper;

    /*寻找所有音乐*/
    @Override
    public List<MusicEntity> findallmusic() {
        return musicMapper.findallmusic();
    }

    /*查询某个id的音乐信息*/
    @Override
    public MusicEntity findmusicById(int musicid) {
        return musicMapper.findMusicById(musicid);
    }

    /*搜索推荐的音乐*/
    @Override
    public List<MusicEntity> findmusicrecommend() {
        return musicMapper.findmusicrecommend();
    }

    /*按名字查询书籍*/
    @Override
    public List<MusicEntity> findmusicBymusicname(String musicname) {
        return musicMapper.findmusicBymusicname(musicname);
    }

    /*设置音乐推荐*/
    @Override
    public void setMusicrecommend(String recommend, int musicid) {
        musicMapper.setMusicrecommend(recommend,musicid);
    }

    /*添加新的音乐*/
    @Override
    public void addnewmusic(String musicname, String singer, String publisher, String briefintro, String pubdate, String musictype) {
        musicMapper.addnewmusic(musicname,singer,publisher,briefintro,pubdate,musictype);
    }

    /*更新音乐信息*/
    @Override
    public int updatemusic(int musicid, String musicname, String singer, String publisher, String pubdate, String briefintro) {
        return musicMapper.updatemusic(musicid,musicname,singer,publisher,pubdate,briefintro);
    }

    /*删除音乐*/
    @Override
    public int delmusic(int musicid) {
        return musicMapper.delmusic(musicid);
    }

    /*更新音乐封面*/
    @Override
    public int updateMusicprc(int musicid) {
        return musicMapper.updateMusicprc(musicid);
    }


}
