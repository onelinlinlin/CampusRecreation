package com.lzp.controller.user;


import com.lzp.entity.UserEntity;
import com.lzp.service.UserService;
import jdk.nashorn.internal.objects.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    /*登陆检查*/
    @RequestMapping(value = "/userlogincheck")
    @ResponseBody
    public String userlogincheck(@RequestParam("username")  String username, @RequestParam("password") String password, HttpServletRequest request, HttpSession session, Model model) {
        UserEntity userEntity = userService.checkLogin(username,password);

        if(userEntity == null){
            return "fail";
        }else {
            if (userEntity.getState().equals("封禁")) {
                return "封禁";
            } else {
                session.setAttribute("useridsession",userEntity.getUserid());
                session.setAttribute("usernamesession",username);
                session.setAttribute("userpassswordsession",password);
                return "success";
            }
        }

    }

    /*用户登陆界面*/
    @RequestMapping(value = "/userlogin")
    public String userlogin() {
        return "user/userlogin";
    }

    /*用户注册界面*/
    @RequestMapping(value = "/userregister")
    public String userregister() {
        return "user/userregister";
    }


    /*实现用户注册*/
    @RequestMapping(value = "/addUser")
    @ResponseBody
    public String addUser(@RequestParam("username")  String username, @RequestParam("password") String password) {

        /*判断用户名是否存在*/
        boolean flag = findusernamebyun(username);

        if (flag == true) {
            return "fail";
        } else {
            /*执行注册操作*/
            userService.addnewUser(username,password);

            return "success";
        }

    }

    /*注册检查*/
    public boolean findusernamebyun(String username) {

        System.out.println(username);

        String usernamecheck = userService.finduserByusername(username);
        System.out.println(usernamecheck);

        if (usernamecheck != null) {
            return true;
        } else {
            return false;
        }

    }


    /*判断用户是否登陆*/
    @RequestMapping(value = "/useriflogin")
    @ResponseBody
    public Map<String,Object> useriflogin(HttpServletRequest request, HttpSession session, Model model) {

        Map<String,Object> userinfo = new HashMap<>();

        if (session.getAttribute("useridsession") != null && session.getAttribute("usernamesession") != null
                && session.getAttribute("userpassswordsession") !=null){

            /*获取用户信息*/
            int userid = (int) session.getAttribute("useridsession");
            UserEntity userEntity = userService.finduserById(userid);

            userinfo.put("iflogin","userislogin");
            userinfo.put("userdata",userEntity);

            return userinfo;

        } else {

            userinfo.put("iflogin","usernologin");
            userinfo.put("userdata",null);
            return userinfo;
        }

    }

    // 用户退出登陆操作
    /*退出登录操作*/
    @RequestMapping(value = "/logout")
    public String adminlogout(HttpServletRequest request, HttpSession session) {

        // 删除会话
        session.removeAttribute("useridsession");
        session.removeAttribute("usernamesession");
        session.removeAttribute("userpassswordsession");

        return "reception/index";
    }


    /*
    * 用户信息模块
    * */


    /*更新用户信息*/
    @PostMapping(value = "/updateUserinfo")
    @ResponseBody
    public String update(HttpSession session,String userNickname,String userphone,String userSex) {

        if (session.getAttribute("useridsession") != null && session.getAttribute("usernamesession") != null
                && session.getAttribute("userpassswordsession") != null){

            /*用户id*/
            int userid = (int) session.getAttribute("useridsession");

            /*更新用户信息*/
            userService.updateUserinfo(userNickname,userphone,userSex,userid);

            return "修改个人信息成功";
        } else {
            return "用户未登录";
        }

    }


    /*更新用户头像*/
    @RequestMapping(value = "/uploadfile")
    @ResponseBody
    public Map<String,Object> uploadfile(HttpSession session, MultipartFile file) {

        if (session.getAttribute("useridsession") != null && session.getAttribute("usernamesession") != null
                && session.getAttribute("userpassswordsession") !=null){

            Map<String,Object> map = new HashMap<>();

            /*用户名*/
            String headprc = session.getAttribute("useridsession").toString();

            if (file != null){
                try {
                    String filepath = "D:\\java应用技术课设\\JavaEECS\\CampusRecreation\\src\\main\\resources\\static\\prc\\headprc\\"+headprc+".jpg";

                    file.transferTo(Paths.get(filepath));

                    map.put("uploaded","成功");

                    /*更新用户头像信息*/
                    userService.updateUserprc((int)session.getAttribute("useridsession"),headprc);

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

    /*查找所有用户*/
    @RequestMapping("/findAllUser")
    @ResponseBody
    public List<UserEntity> findAllUser(){
        return userService.findAllUserByRole();
    }


    /*更新用户状态*/
    @RequestMapping("/userStateEdit")
    @ResponseBody
    public String userStateEdit(@RequestParam int userid,@RequestParam String state){
        System.out.println(userid);
        int result = userService.updateUserState(userid,state);

        if (result > 0 ) {
            return "操作成功";
        } else {
            return "操作失败";
        }
    }


    /*删除用户*/
    @RequestMapping("/deluser")
    @ResponseBody
    public String deluser(@RequestParam int userid){

        int result = userService.delUser(userid);
        if (result > 0 ) {
            return "操作成功";
        } else {
            return "操作失败";
        }
    }
}
