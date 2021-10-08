package com.lzp.service.impl;

import com.lzp.entity.ActivityEntity;
import com.lzp.entity.ActivityenlistEntity;
import com.lzp.mapper.ActivityMapper;
import com.lzp.mapper.ActivityenlistMapper;
import com.lzp.service.ActivityService;
import com.lzp.service.ActivityenlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityenlistServiceImpl implements ActivityenlistService {
    @Autowired
    private ActivityenlistMapper activityenlistMapper;

    /*查找该活动的所有随笔*/
    @Override
    public List<ActivityenlistEntity> findallessay(int activityid) {
        return activityenlistMapper.findallessay(activityid);
    }

    /*通过用户id，活动id查找报名记录*/
    @Override
    public ActivityenlistEntity findenlist(int activityid, int userid) {
        return activityenlistMapper.findenlist(activityid,userid);
    }

    /*提交用户报名*/
    @Override
    public ActivityenlistEntity submitenlist(int activityid, int userid, String phone, String name) {
        return activityenlistMapper.submitenlist(activityid,userid,phone,name);
    }

    /*更新用户的活动随笔*/
    @Override
    public void updateessay(String essay, int activityid, int userid) {
         activityenlistMapper.updateessay(essay,activityid,userid);
    }

    /*通过用户id查找报名记录*/
    @Override
    public List<Integer> finduserjoin(int userid) {
        return activityenlistMapper.finduserjoin(userid);
    }
}
