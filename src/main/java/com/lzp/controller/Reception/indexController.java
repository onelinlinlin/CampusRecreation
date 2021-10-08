package com.lzp.controller.Reception;

import com.lzp.entity.BookEntity;
import com.lzp.entity.MovieEntity;
import com.lzp.entity.MusicEntity;
import com.lzp.service.BookService;
import com.lzp.service.MovieService;
import com.lzp.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class indexController {

    /*前往用户主界面*/
    @RequestMapping(value = "/index")
    public String userIndex() {
        return "reception/index";
    }

    /*前往用户读书主界面*/
    @RequestMapping(value = "/bookhome")
    public String bookhome() {
        return "reception/bookhome";
    }

    /*前往用户电影主界面*/
    @RequestMapping(value = "/moviehome")
    public String moviehome() {
        return "reception/moviehome";
    }

    /*前往用户音乐主界面*/
    @RequestMapping(value = "/musichome")
    public String musichome() {
        return "reception/musichome";
    }

    /*前往用户活动主界面*/
    @RequestMapping(value = "/activityhome")
    public String activityhome() {
        return "reception/activityhome";
    }

    /*前往用户信息管理主界面*/
    @RequestMapping(value = "/userinfohome")
    public String userinfohome() {
        return "reception/userinfohome";
    }

    /*前往搜索主界面*/
    @RequestMapping(value = "/searchhome")
    public String searchhome() {
        return "search/search";
    }


    @Autowired
    private BookService bookService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private MusicService musicService;

    /*搜索处理*/
    @RequestMapping(value = "/searchsomething")
    @ResponseBody
    public Map<String,Object> searchsomething(@RequestParam String type,@RequestParam String searchstr) {

        Map<String,Object> map = new HashMap<>();

        if (type.equals("book")) {
            List<BookEntity> searchlist = bookService.findBookBybookname(searchstr);
            map.put("result1","book");
            map.put("result2",searchlist);
            return map;
        } else if (type.equals("music")) {
            List<MusicEntity> searchlist = musicService.findmusicBymusicname(searchstr);
            map.put("result1","music");
            map.put("result2",searchlist);
            return map;
        } else {
            List<MovieEntity> searchlist = movieService.findMovieBymoviename(searchstr);
            map.put("result1","movie");
            map.put("result2", searchlist);
            return map;
        }

    }


}
