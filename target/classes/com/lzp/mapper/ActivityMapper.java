package com.lzp.mapper;

import com.lzp.entity.ActivityEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ActivityMapper {

    /*搜索所有的活动*/
    public List<ActivityEntity> findallactivity();

    /*按id搜索*/
    public ActivityEntity findactivitybyid(@Param("activityid") int activityid);

    /*通过发起人id查询户外活动*/
    public List<ActivityEntity> findactivitybyuserid(@Param("userid") int userid);

    /*用户发布新的活动*/
    public void addnewactivity(@Param("userid") int userid,@Param("activityname") String activityname,@Param("activityplace") String activityplace,
                               @Param("activityintro") String activityintro,@Param("activitydate") String activitydate,@Param("activitytime") String activitytime);

    /*通过活动简介和活动名称来查找id*/
    public int findactivitybyactivityintro(@Param("activityintro") String activityintro,@Param("activityname") String activityname);

    /*更新活动图片*/
    public void updateactivityprc(@Param("activityid") int activityid);

    /*更新活动状态*/
    public int updateactivitystate(@Param("activityid") int activityid, @Param("state") String state);

    /*删除活动*/
    public int delactivitybyid(@Param("activityid") int activityid);

    /*审核通过的活动*/
    public  List<ActivityEntity> findactivitybystate();
}
