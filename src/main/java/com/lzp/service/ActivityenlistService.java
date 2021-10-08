package com.lzp.service;

import com.lzp.entity.ActivityEntity;
import com.lzp.entity.ActivityenlistEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActivityenlistService {

    /*查找该活动的所有随笔*/
    public List<ActivityenlistEntity> findallessay(int activityid);

    /*通过用户id，活动id查找报名记录*/
    public ActivityenlistEntity findenlist(int activityid,int userid);


    /*提交用户报名*/
    public ActivityenlistEntity submitenlist(int activityid,int userid,  String phone, String name);

    /*更新用户的活动随笔*/
    public void updateessay(String essay,@Param("activityid") int activityid, int userid);

    /*通过用户id查找报名记录*/
    public List<Integer> finduserjoin(int userid);
}
