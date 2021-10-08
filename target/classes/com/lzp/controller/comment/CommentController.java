package com.lzp.controller.comment;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzp.entity.CommentEntity;
import com.lzp.entity.UserEntity;
import com.lzp.service.CommentService;
import com.lzp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    /*找到所有书的评论*/
    @RequestMapping(value = "/findBookComment")
    @ResponseBody
    public Map<String, Object> findallbook(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                           @RequestParam(defaultValue = "5") Integer pageSize, int bookid) {

        try {
            List<CommentEntity> commentEntities = commentService.findBookComment(bookid);
            PageHelper.startPage(pageNum, pageSize);
            PageInfo pageInfo = new PageInfo(commentService.findBookComment(bookid));

            //直接返回json字符串
            Map<String, Object> comment = new HashMap<>();

            comment.put("commentNum",commentEntities.size());
            comment.put("commentlist",pageInfo.getList());

            return comment;
        } catch (Exception e) {
            return null;
        }
    }


    /*找到所有电影的评论*/
    @RequestMapping(value = "/findMovieComment")
    @ResponseBody
    public Map<String, Object> findallmovie(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                           @RequestParam(defaultValue = "5") Integer pageSize, int movieid) {
        try {
            List<CommentEntity> commentEntities = commentService.findMovieComment(movieid);
            PageHelper.startPage(pageNum, pageSize);
            PageInfo pageInfo = new PageInfo(commentService.findMovieComment(movieid));

            //直接返回json字符串
            Map<String, Object> comment = new HashMap<>();

            comment.put("commentNum",commentEntities.size());
            comment.put("commentlist",pageInfo.getList());

            return comment;
        } catch (Exception e) {
            return null;
        }
    }

    /*找到所有音乐的评论*/
    @RequestMapping(value = "/findMusicComment")
    @ResponseBody
    public Map<String, Object> findallmusiccomment(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                            @RequestParam(defaultValue = "5") Integer pageSize, int musicid) {
        try {
            List<CommentEntity> commentEntities = commentService.findMovieComment(musicid);
            PageHelper.startPage(pageNum, pageSize);
            PageInfo pageInfo = new PageInfo(commentService.findMusicComment(musicid));

            //直接返回json字符串
            Map<String, Object> comment = new HashMap<>();

            comment.put("commentNum",commentEntities.size());
            comment.put("commentlist",pageInfo.getList());

            return comment;
        } catch (Exception e) {
            return null;
        }
    }


    /*按userid查询内容*/
    @RequestMapping(value = "/finduser")
    @ResponseBody
    public UserEntity findNickname(int userid) {
        try {
            UserEntity userinfo = userService.finduserById(userid);
            return userinfo;
        } catch (Exception e) {
            return null;
        }
    }

    /*用户提交图书评论*/
    @RequestMapping(value = "/submitbookComment")
    @ResponseBody
    public String submitbookComment(@RequestParam String bookcomment, @RequestParam int bookid, HttpSession session) {

        if (session.getAttribute("useridsession") != null && session.getAttribute("usernamesession") != null
                && session.getAttribute("userpassswordsession") !=null){

            // 获得用户id
            int userid = (int) session.getAttribute("useridsession");

            String type = "book";

            // 获得当前时间
            Date date = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String commentdate = format.format(date);

            // 执行提交
            commentService.submitcomment(type,userid,bookcomment,bookid,commentdate);

            return "提交书籍评论成功";

        } else {
            return "用户未登录";
        }

    }

    /*用户提交电影评论*/
    @RequestMapping(value = "/submitmovieComment")
    @ResponseBody
    public String submitmovieComment(@RequestParam String moviecomment, @RequestParam int movieid, HttpSession session) {

        if (session.getAttribute("useridsession") != null && session.getAttribute("usernamesession") != null
                && session.getAttribute("userpassswordsession") !=null){

            // 获得用户id
            int userid = (int) session.getAttribute("useridsession");

            String type = "movie";

            // 获得当前时间
            Date date = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String commentdate = format.format(date);

            // 执行提交
            commentService.submitcomment(type,userid,moviecomment,movieid,commentdate);

            return "提交电影评论成功";

        } else {
            return "用户未登录";
        }

    }

    /*用户提交音乐评论*/
    @RequestMapping(value = "/submitmusicComment")
    @ResponseBody
    public String submitmusicComment(@RequestParam String musiccomment, @RequestParam int musicid, HttpSession session) {

        if (session.getAttribute("useridsession") != null && session.getAttribute("usernamesession") != null
                && session.getAttribute("userpassswordsession") !=null){

            // 获得用户id
            int userid = (int) session.getAttribute("useridsession");

            String type = "music";

            // 获得当前时间
            Date date = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String commentdate = format.format(date);

            // 执行提交
            commentService.submitcomment(type,userid,musiccomment,musicid,commentdate);

            return "提交音乐评论成功";

        } else {
            return "用户未登录";
        }

    }
}
