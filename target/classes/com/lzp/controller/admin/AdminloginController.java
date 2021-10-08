package com.lzp.controller.admin;

import com.lzp.entity.UserEntity;
import com.lzp.service.UserService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class AdminloginController {
    @Autowired
    private UserService userService;

    /*管理员登陆验证*/
    @RequestMapping(value = "/adminlogincheck")
    @ResponseBody
    public String adminlogincheck(@RequestParam("username")  String username, @RequestParam("password") String password, HttpServletRequest request, HttpSession session, Model model) {
        UserEntity userEntity = userService.checkLogin(username,password);

        if(userEntity == null){
            return "fail";
        }else {
            /*判断是不是管理员*/
            if (userEntity.getRole().equals("管理员")) {
                session.setAttribute("adminidsession",userEntity.getUserid());
                session.setAttribute("adminnamesession",username);
                session.setAttribute("adminpassswordsession",password);
                return "success";
            } else {
                return "noadmin";
            }

        }

    }

    /*管理员登陆*/
    @RequestMapping(value = "/adminlogin")
    public String adminlogin(HttpSession session) {

        if (session.getAttribute("adminnamesession") == null && session.getAttribute("adminpassswordsession")==null) {
            return "admin/adminlogin";
        } else {
            return "admin/adminhome";
        }

    }

    /*管理员主页*/
    @RequestMapping(value = "/adminhome")
    public String adminhome(HttpServletRequest request, HttpSession session, Map<String,Object> result) {

        System.out.println(session.getAttribute("adminidsession")+""+
                session.getAttribute("adminnamesession")+" "+session.getAttribute("adminpassswordsession"));

        /*没有保存会话 就跳转到登陆页面*/
        if (session.getAttribute("adminnamesession") == null && session.getAttribute("adminpassswordsession")==null) {
            return "admin/adminlogin";
        } else {

            /*用户信息*/
            int adminid = (int) session.getAttribute("adminidsession");

            UserEntity userEntity = userService.finduserById(adminid);


            /*判断该角色是不是管理员*/
            if (userEntity.getRole().equals("管理员")) {
                result.put("admindata",userEntity);
                return "admin/adminhome";
            } else {
                return "admin/adminlogin";
            }
        }

    }

}
