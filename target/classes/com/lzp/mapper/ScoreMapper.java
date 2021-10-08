package com.lzp.mapper;

import com.lzp.entity.ScoreEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScoreMapper {

    /*通过用户名，类别，类别id查找评分*/
    public ScoreEntity findscore(@Param("userid") int userid, @Param("type") String type, @Param("typeid") int typeid);

    /*搜索该类型id下的所有评分*/
    public List<Integer> findscorelist(@Param("type") String type, @Param("typeid") int typeid);

    /*将新的评分加入到表中*/
    public void addnewuserscore(@Param("scores")int scores, @Param("userid") int userid,@Param("type") String type,@Param("typeid") int typeid);

    /*更新图书评分*/
    public void updatebookescore(@Param("scores") int scores, @Param("typeid") int typeid);

    /*更新电影评分*/
    public void updatemovieescore(@Param("scores") int scores, @Param("typeid") int typeid);

    /*更新音乐评分*/
    public void updatemusicescore(@Param("scores") int scores, @Param("typeid") int typeid);

}
