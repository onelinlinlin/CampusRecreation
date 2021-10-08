package com.lzp.service.impl;

import com.lzp.entity.ScoreEntity;
import com.lzp.mapper.ScoreMapper;
import com.lzp.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    /*通过用户名，类别，类别id查找评分*/
    public ScoreEntity findscore(int userid,String type,int typeid) {
        return scoreMapper.findscore(userid, type, typeid);
    }

    /*搜索该类型id下的所有评分*/
    @Override
    public List<Integer> findscorelist(String type, int typeid) {
        return scoreMapper.findscorelist(type,typeid);
    }

    /*将新的评分加入到表中*/
    @Override
    public void addnewuserscore(int scores, int userid, String type, int typeid) {
         scoreMapper.addnewuserscore(scores, userid, type, typeid);
    }

    /*更新图书评分*/
    @Override
    public void updatebookescore(int scores, int typeid) {
        scoreMapper.updatebookescore(scores,typeid);
    }

    /*更新电影评分*/
    @Override
    public void updatemovieescore(int scores, int typeid) {
        scoreMapper.updatemovieescore(scores,typeid);
    }

    /*更新音乐评分*/
    @Override
    public void updatemusicescore(int scores, int typeid) {
        scoreMapper.updatemusicescore(scores,typeid);
    }

}
