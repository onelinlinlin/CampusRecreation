package com.lzp.service;

import com.lzp.entity.ActivityEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActivityService {

    /*搜索所有的活动*/
    public List<ActivityEntity> findallactivity();

    /*按id搜索*/
    public ActivityEntity findactivitybyid(int activityid);

    /*通过发起人id查询户外活动*/
    public List<ActivityEntity> findactivitybyuserid(int userid);

    /*用户发布新的活动*/
    public void addnewactivity(int userid,String activityname,String activityplace,String activityintro,String activitydate,String activitytime);

    /*通过活动简介和活动名称来查找id*/
    public int findactivitybyactivityintro(String activityintro,String activityname);

    /*更新活动图片*/
    public void updateactivityprc(int activityid);

    /*更新活动状态*/
    public int updateactivitystate(int activityid,String state);

    /*删除活动*/
    public int delactivitybyid(int activityid);

    /*审核通过的活动*/
    public  List<ActivityEntity> findactivitybystate();
}
