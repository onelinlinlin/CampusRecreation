package com.lzp.mapper;

import com.lzp.entity.ActivityenlistEntity;
import com.lzp.entity.ScoreEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ActivityenlistMapper {

    /*查找该活动的所有随笔*/
    public List<ActivityenlistEntity> findallessay(@Param("activityid") int activityid);

    /*通过用户id，活动id查找报名记录*/
    public ActivityenlistEntity findenlist(@Param("activityid") int activityid, @Param("userid") int userid);

    /*提交用户报名*/
    public ActivityenlistEntity submitenlist(@Param("activityid") int activityid,@Param("userid") int userid,@Param("phone") String phone,@Param("name") String name);

    /*更新用户的活动随笔*/
    public void updateessay(@Param("essay") String essay,@Param("activityid") int activityid, @Param("userid") int userid);

    /*通过用户id查找报名记录*/
    public List<Integer> finduserjoin(@Param("userid") int userid);
}
