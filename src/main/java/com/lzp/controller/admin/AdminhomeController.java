package com.lzp.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/admin")
public class AdminhomeController {

    /*图书操作跳转*/
    @RequestMapping(value = "/bookedit")
    public String bookedit() {
        return "backstage/bookedit";
    }

    /*添加图书跳转*/
    @RequestMapping(value = "/addbookinfo")
    public String addbookinfo() {
        return "backstage/addbookinfo";
    }

    /*电影操作跳转*/
    @RequestMapping(value = "/movieedit")
    public String movieedit() {
        return "backstage/movieedit";
    }

    /*添加电影跳转*/
    @RequestMapping(value = "/addmovieinfo")
    public String addmovieinfo() {
        return "backstage/addmovieinfo";
    }

    /*音乐操作跳转*/
    @RequestMapping(value = "/musicedit")
    public String musicedit() {
        return "backstage/musicedit";
    }

    /*添加音乐跳转*/
    @RequestMapping(value = "/addmusicinfo")
    public String addmusicinfo() {
        return "backstage/addmusicinfo";
    }

    /*书籍推荐跳转*/
    @RequestMapping(value = "/bookRecommend")
    public String bookRecommend() {
        return "backstage/bookRecommend";
    }

    /*音乐推荐跳转*/
    @RequestMapping(value = "/musicRecommend")
    public String musicRecommend() {
        return "backstage/musicRecommend";
    }

    /*影片推荐跳转*/
    @RequestMapping(value = "/movieRecommend")
    public String movieRecommend() {
        return "backstage/movieRecommend";
    }

    /*活动管理跳转*/
    @RequestMapping(value = "/usermanage")
    public String usermanage() {
        return "backstage/usermanage";
    }

    /*用户管理跳转*/
    @RequestMapping(value = "/activitymanage")
    public String activitymanage() {
        return "backstage/activitymanage";
    }

    /*退出登录操作*/
    @RequestMapping(value = "/logout")
    public String adminlogout(HttpServletRequest request, HttpSession session) {

        System.out.println(session.getAttribute("adminnamesession")+" "+session.getAttribute("adminpassswordsession"));

        // 删除会话
        session.removeAttribute("adminidsession");
        session.removeAttribute("adminnamesession");
        session.removeAttribute("adminpassswordsession");

        return "admin/adminlogin";
    }

}
