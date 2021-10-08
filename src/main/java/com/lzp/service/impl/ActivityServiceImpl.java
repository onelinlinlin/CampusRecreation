package com.lzp.service.impl;

import com.lzp.entity.ActivityEntity;
import com.lzp.mapper.ActivityMapper;
import com.lzp.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    /*搜索所有的活动*/
    @Override
    public List<ActivityEntity> findallactivity() {
        return activityMapper.findallactivity();
    }

    /*按id搜索*/
    @Override
    public ActivityEntity findactivitybyid(int activityid) {
        return activityMapper.findactivitybyid(activityid);
    }

    /*通过发起人id查询户外活动*/
    @Override
    public List<ActivityEntity> findactivitybyuserid(int userid) {
        return activityMapper.findactivitybyuserid(userid);
    }

    /*用户发布新的活动*/
    @Override
    public void addnewactivity(int userid, String activityname, String activityplace, String activityintro, String activitydate, String activitytime) {
        activityMapper.addnewactivity(userid,activityname,activityplace,activityintro,activitydate,activitytime);
    }

    /*通过活动简介和活动名称来查找id*/
    @Override
    public int findactivitybyactivityintro(String activityintro, String activityname) {
        return activityMapper.findactivitybyactivityintro(activityintro,activityname);
    }

    /*更新活动图片*/
    @Override
    public void updateactivityprc(int activityid) {
        activityMapper.updateactivityprc(activityid);
    }

    /*更新活动状态*/
    @Override
    public int updateactivitystate(int activityid, String state) {
        return activityMapper.updateactivitystate(activityid,state);
    }

    /*删除活动*/
    @Override
    public int delactivitybyid(int activityid) {
        return activityMapper.delactivitybyid(activityid);
    }

    /*审核通过的活动*/
    @Override
    public List<ActivityEntity> findactivitybystate() {
        return activityMapper.findactivitybystate();
    }

}
