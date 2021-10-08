package com.lzp.controller.movie;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzp.entity.BookEntity;
import com.lzp.entity.MovieEntity;
import com.lzp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/findallmovie")
    @ResponseBody
    public Map<String, Object> findallmovie(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                          @RequestParam(defaultValue = "2") Integer pageSize) {
        try {
            List<MovieEntity> movieEntity = movieService.findallmovie();
            PageHelper.startPage(pageNum, pageSize);
            PageInfo pageInfo = new PageInfo(movieService.findallmovie());

            //直接返回json字符串
            Map<String, Object> movie = new HashMap<>();

            movie.put("movieNum",movieEntity.size());
            movie.put("movielist",pageInfo.getList());

            return movie;
        } catch (Exception e) {
            return null;
        }
    }

    /*寻找所有电影*/
    @RequestMapping(value = "/findallmovie2")
    @ResponseBody
    public List<MovieEntity> findallmovie2() {
        try {
            List<MovieEntity> movieEntities = movieService.findallmovie();
            return movieEntities;
        } catch (Exception e) {
            return null;
        }
    }

    /*设置电影推荐*/
    @RequestMapping(value = "/setMovierecommend")
    @ResponseBody
    public String setMovierecommend(@RequestParam String recommend,@RequestParam int movieid) {
        movieService.setMovierecommend(recommend,movieid);
        return "操作成功";
    }

    /*添加新的电影*/
    @RequestMapping(value = "/addnewmovie")
    @ResponseBody
    public String addnewmovie(@RequestParam String moviename,@RequestParam String director,@RequestParam String language,@RequestParam String showtime,
                              @RequestParam String duration,@RequestParam String actor,@RequestParam String briefintro) {
        movieService.addnewmovie(moviename,director,language,showtime,duration,actor,briefintro);
        return "操作成功";
    }


    /*跳转电影详情*/
    @RequestMapping(value = "/moviedetails")
    public String findallmovie1(int movieid, Map<String,Object> result) {
        MovieEntity movieEntity = movieService.findMovieById(movieid);

        result.put("movieEntity",movieEntity);

        if(movieEntity == null){
            return null;
        }else {
            return "/movie/moviedetails";
        }
    }

    /*搜索被推荐的书*/
    @RequestMapping(value = "/findmovierecommend")
    @ResponseBody
    public Map<String, Object> findmovierecommend(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                                 @RequestParam(defaultValue = "2") Integer pageSize) {
        try {
            List<MovieEntity> movieEntity = movieService.findmovierecommend();
            PageHelper.startPage(pageNum, pageSize);
            PageInfo pageInfo = new PageInfo(movieService.findmovierecommend());

            //直接返回json字符串
            Map<String, Object> movie = new HashMap<>();

            movie.put("movieNum",movieEntity.size());
            movie.put("movielist",pageInfo.getList());

            return movie;
        } catch (Exception e) {
            return null;
        }
    }


    /*更新图书*/
    @RequestMapping(value = "/updatemovie")
    @ResponseBody
    public String updatemovie(@RequestParam int movieid,@RequestParam String moviename,@RequestParam String director,@RequestParam String language,
                              @RequestParam String showtime,@RequestParam String duration,@RequestParam String actor,@RequestParam String briefintro) {

        int result =  movieService.updatemovie(movieid,moviename,director,language,showtime,duration,actor,briefintro);

        if (result > 0 ) {
            return "操作成功";
        } else {
            return "操作失败";
        }
    }


    /*删除图书*/
    @RequestMapping(value = "/delmovie")
    @ResponseBody
    public String delmovie(@RequestParam int movieid) {
        int result =  movieService.delmovie(movieid);

        if (result > 0 ) {
            return "操作成功";
        } else {
            return "操作失败";
        }
    }

    /*更新图书的封面*/
    @RequestMapping(value = "/upmovieprc")
    @ResponseBody
    public Map<String,Object> upmovieprc(HttpSession session, MultipartFile file, @RequestParam int movieid) {

        Map<String,Object> map = new HashMap<>();

        if (file != null){
            try {
                String filepath = "D:\\java应用技术课设\\JavaEECS\\CampusRecreation\\src\\main\\resources\\static\\prc\\movieprc\\"+movieid+".jpg";

                file.transferTo(Paths.get(filepath));

                map.put("uploaded","上传成功");

                /*更新图书封面信息*/
                movieService.updateMovieprc(movieid);

                return map;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            map.put("error","上传失败");
            return map;
        }
        return null;
    }

}
