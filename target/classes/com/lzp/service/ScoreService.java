package com.lzp.service;

import com.lzp.entity.ScoreEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScoreService {

    /*通过用户名，类别，类别id查找评分*/
    public ScoreEntity findscore(int userid,String type,int typeid);

    /*搜索该类型id下的所有评分*/
    public List<Integer> findscorelist(String type, int typeid);

    /*将新的评分加入到表中*/
    public void addnewuserscore(int scores, int userid, String type, int typeid);

    /*更新图书评分*/
    public void updatebookescore(int scores, int typeid);

    /*更新电影评分*/
    public void updatemovieescore(int scores, int typeid);

    /*更新音乐评分*/
    public void updatemusicescore(int scores, int typeid);
}
