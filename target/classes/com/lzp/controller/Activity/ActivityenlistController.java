package com.lzp.controller.Activity;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzp.entity.ActivityEntity;
import com.lzp.entity.ActivityenlistEntity;
import com.lzp.entity.BookEntity;
import com.lzp.service.ActivityService;
import com.lzp.service.ActivityenlistService;
import com.lzp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/activityenlist")
public class ActivityenlistController {

    @Autowired
    private ActivityenlistService activityenlistService;
    @Autowired
    private UserService userService;


    /*查找所有活动随笔*/
    @RequestMapping(value = "/findallessay")
    @ResponseBody
    public Map<String, Object> findallbook(@RequestParam int activityid, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                           @RequestParam(defaultValue = "2") Integer pageSize) {
        try {
            List<ActivityenlistEntity> activityenlistEntities = activityenlistService.findallessay(activityid);
            PageHelper.startPage(pageNum, pageSize);
            PageInfo pageInfo = new PageInfo(activityenlistService.findallessay(activityid));

            //直接返回json字符串
            Map<String, Object> essay = new HashMap<>();

            essay.put("essayNum",activityenlistEntities.size());
            essay.put("essaylist",pageInfo.getList());

            return essay;
        } catch (Exception e) {
            return null;
        }
    }


    /*提交随笔*/
    @RequestMapping(value = "/submitessay")
    @ResponseBody
    public String submitessay(@RequestParam String essay, @RequestParam int activityid, HttpSession session) {

        if (session.getAttribute("useridsession") != null && session.getAttribute("usernamesession") != null
                && session.getAttribute("userpassswordsession") !=null){

            // 获得用户id
            int userid = (int) session.getAttribute("useridsession");

            /*判断该用户有没有参加活动*/
            if (activityenlistService.findenlist(activityid,userid) != null) {

                /*判断用户是否提交过随笔*/
                if (activityenlistService.findenlist(activityid,userid).getEssay().equals("")) {
                    // 执行提交
                    activityenlistService.updateessay(essay,activityid,userid);

                    return "提交活动随笔成功";
                } else {
                    return "该用户已经评论过了";
                }
            } else {
                return "该用户未报名";
            }
        } else {
            return "用户未登录";
        }

    }

    /*判断是否已截至*/
    @RequestMapping(value = "/ifdatestop")
    @ResponseBody
    public String submitessay(@RequestParam String activitydate) {

        // 获得当前时间
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String newdate = format.format(date);

        Long between_dayInteger = between_days(newdate, activitydate);

        if ( between_dayInteger >= 0) {
            return "可以报名";
        } else {
            return "报名截止";
        }

    }

    /*计算两个日期字符串的时间差*/
    public Long between_days(String a, String b) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// 自定义时间格式

        Calendar calendar_a = Calendar.getInstance();// 获取日历对象
        Calendar calendar_b = Calendar.getInstance();

        Date date_a = null;
        Date date_b = null;

        try {
            date_a = simpleDateFormat.parse(a);//字符串转Date
            date_b = simpleDateFormat.parse(b);
            calendar_a.setTime(date_a);// 设置日历
            calendar_b.setTime(date_b);
        } catch (ParseException e) {
            e.printStackTrace();//格式化异常
        }

        long time_a = calendar_a.getTimeInMillis();
        long time_b = calendar_b.getTimeInMillis();

        long between_days = (time_b - time_a) / (1000 * 3600 * 24);//计算相差天数

        return between_days;
    }


    /*插入新的报名*/
    @RequestMapping(value = "/submitenlist")
    @ResponseBody
    public String submitenlist(String phone,String name,int activityid, String activitydate, HttpSession session) {

        // 获得当前时间
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String newdate = format.format(date);

        Long between_dayInteger = between_days(newdate, activitydate);


        if (session.getAttribute("useridsession") != null && session.getAttribute("usernamesession") != null
                && session.getAttribute("userpassswordsession") != null){

            if (between_dayInteger >= 0){
                /*获取用户编号*/
                int userid = (int) session.getAttribute("useridsession");

                /*判断是否报名过*/
                if (activityenlistService.findenlist(activityid,userid) != null) {
                    return "该用户已报名";
                } else {
                    activityenlistService.submitenlist(activityid,userid,phone,name);
                    return "报名成功";
                }
            } else {
                return "报名时间已过";
            }

        } else {
            return "用户未登录";
        }

    }



}
