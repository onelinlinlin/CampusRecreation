package com.lzp.controller.Activity;

import com.lzp.entity.ActivityEntity;
import com.lzp.entity.ActivityenlistEntity;
import com.lzp.entity.UserEntity;
import com.lzp.service.ActivityService;
import com.lzp.service.ActivityenlistService;
import com.lzp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

@Controller
@RequestMapping(value = "/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserService userService;
    @Autowired
    private ActivityenlistService activityenlistService;

    /*得到所有的活动*/
    @RequestMapping(value = "/findallactivity")
    @ResponseBody
    public List<ActivityEntity> findallmusic() {
        try {
            List<ActivityEntity> activityEntities = activityService.findallactivity();

            //直接返回json字符串
            return activityEntities;

        } catch (Exception e) {
            return null;
        }
    }

    /*得到审核通过的活动*/
    @RequestMapping(value = "/findactivitybystate")
    @ResponseBody
    public List<ActivityEntity> findactivitybystate() {
        try {
            List<ActivityEntity> activityEntities = activityService.findactivitybystate();

            //直接返回json字符串
            return activityEntities;

        } catch (Exception e) {
            return null;
        }
    }

    /*跳转到活动详情页面*/
    @RequestMapping(value = "/activitydetails")
    public String activitydetails(int activityid, Map<String,Object> result) {

        ActivityEntity activityEntity = activityService.findactivitybyid(activityid);

        result.put("activityEntity",activityEntity);

        /*通过userid找到发起者*/
        UserEntity userEntity = userService.finduserById(activityEntity.getUserid());

        result.put("nickname",userEntity.getNickname());

        if(activityEntity == null){
            return null;
        }else {
            return "activity/activitydetails";
        }
    }

    /*跳转到活动详情页面*/
    @RequestMapping(value = "/activityprcsubmit")
    public String activityprcsubmit(int activityid, Map<String,Object> result) {

        ActivityEntity activityEntity = activityService.findactivitybyid(activityid);

        result.put("activityEntity",activityEntity);

        /*通过userid找到发起者*/
        UserEntity userEntity = userService.finduserById(activityEntity.getUserid());

        result.put("nickname",userEntity.getNickname());

        if(activityEntity == null){
            return null;
        }else {
            return "activity/activityprcsubmit";
        }
    }


    /*得到用户参与的全部活动*/
    @RequestMapping(value = "/findjoinactivity")
    @ResponseBody
    public List<ActivityEntity> findjoinactivity(HttpSession session){

        if (session.getAttribute("useridsession") != null && session.getAttribute("usernamesession") != null
                && session.getAttribute("userpassswordsession") != null) {

            int userid = (int)session.getAttribute("useridsession");


            /*通过用户名查找报名记录*/
            List<Integer> activityenlistEntities = activityenlistService.finduserjoin(userid);

            if (activityenlistEntities.size() != 0) {
                /*存放活动*/
                List<ActivityEntity> activityEntities = new ArrayList<>();

                /*遍历得到参与的活动集合*/
                for(int activityid: activityenlistEntities){
                    activityEntities.add(activityService.findactivitybyid(activityid));
                }

                return activityEntities;

            } else {
                return null;
            }

        } else {
            return null;
        }
    }

    /*得到用户发起的活动*/
    @RequestMapping(value = "/findactivitybyuserid")
    @ResponseBody
    public List<ActivityEntity> findactivitybyuserid(HttpSession session){

        if (session.getAttribute("useridsession") != null && session.getAttribute("usernamesession") != null
                && session.getAttribute("userpassswordsession") != null) {

            int userid = (int)session.getAttribute("useridsession");

            List<ActivityEntity> activityEntities = new ArrayList<>();

            activityEntities = activityService.findactivitybyuserid(userid);

            return activityEntities;

        } else {
            return null;
        }
    }

    /*发起新的活动*/
    @RequestMapping(value = "/addnewactivity")
    @ResponseBody
    public String addnewactivity(String activityname,String activityplace,String activitydate,String activitytime, String activityintro, HttpSession session){

        if (session.getAttribute("useridsession") != null && session.getAttribute("usernamesession") != null
                && session.getAttribute("userpassswordsession") != null) {

            int userid = (int) session.getAttribute("useridsession");

            /*进行信息的插入*/
            activityService.addnewactivity(userid,activityname,activityplace,activityintro,activitydate,activitytime);

            return "成功发布活动";
        } else {
            return "用户未登录";
        }
    }

    /*添加活动图片*/
    @RequestMapping(value = "/uploadactivityprc")
    @ResponseBody
    public Map<String,Object> uploadfile(int activityid, HttpSession session, MultipartFile file) {

        if (session.getAttribute("useridsession") != null && session.getAttribute("usernamesession") != null
                && session.getAttribute("userpassswordsession") !=null){

            Map<String,Object> map = new HashMap<>();

                if (file != null){
                    try {
                        String filepath = "D:\\java应用技术课设\\JavaEECS\\CampusRecreation\\src\\main\\resources\\static\\prc\\activityprc\\"+activityid+".jpg";

                        file.transferTo(Paths.get(filepath));

                        map.put("uploaded","成功");

                        /*更新活动图片信息*/
                        activityService.updateactivityprc(activityid);

                        return map;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    map.put("error","上传失败");
                    return map;
                }
        }
        return null;
    }

    /*更新活动状态*/
    @RequestMapping("/activityStateEdit")
    @ResponseBody
    public String activityStateEdit(@RequestParam int activityid,@RequestParam String state){

        int result = activityService.updateactivitystate(activityid,state);

        if (result > 0 ) {
            return "操作成功";
        } else {
            return "操作失败";
        }
    }


    /*删除活动*/
    @RequestMapping("/delactivity")
    @ResponseBody
    public String delactivity(@RequestParam int activityid){

        int result = activityService.delactivitybyid(activityid);

        if (result > 0 ) {
            return "操作成功";
        } else {
            return "操作失败";
        }
    }


    /*发起新活动的链接*/
    @RequestMapping(value = "/newactivity")
    public String newactivity() {
        return "user/newactivity";
    }

    /*跳转到用户的发起活动界面*/
    @RequestMapping(value = "/myactivity")
    public String myactivity(HttpServletRequest request, HttpSession session) {
        return "user/myactivity";
    }

    /*跳转到用户的参与活动界面*/
    @RequestMapping(value = "/myjoinactivity")
    public String myjoinactivity(HttpServletRequest request, HttpSession session) {
        return "user/myjoinactivity";

    }

}
