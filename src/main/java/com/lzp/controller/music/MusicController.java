package com.lzp.controller.music;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzp.entity.MusicEntity;
import com.lzp.service.MusicService;
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
@RequestMapping(value = "/music")
public class MusicController {

    @Autowired
    private MusicService musicService;

    @RequestMapping(value = "/findallmusic")
    @ResponseBody
    public Map<String, Object> findallmusic(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                          @RequestParam(defaultValue = "2") Integer pageSize) {
        try {
            List<MusicEntity> musicEntity = musicService.findallmusic();
            PageHelper.startPage(pageNum, pageSize);
            PageInfo pageInfo = new PageInfo(musicService.findallmusic());

            //直接返回json字符串
            Map<String, Object> music = new HashMap<>();

            music.put("musicNum",musicEntity.size());
            music.put("musiclist",pageInfo.getList());

            return music;
        } catch (Exception e) {
            return null;
        }
    }


    /*寻找所有音乐*/
    @RequestMapping(value = "/findallmusic2")
    @ResponseBody
    public List<MusicEntity> findallmusic2() {
        try {
            List<MusicEntity> musicEntities = musicService.findallmusic();
            return musicEntities;
        } catch (Exception e) {
            return null;
        }
    }

    /*设置音乐推荐*/
    @RequestMapping(value = "/setMusicrecommend")
    @ResponseBody
    public String setMusicrecommend(@RequestParam String recommend,@RequestParam int musicid) {
        musicService.setMusicrecommend(recommend,musicid);
        return "操作成功";
    }

    /*添加新的音乐*/
    @RequestMapping(value = "/addnewmusic")
    @ResponseBody
    public String addnewmusic(@RequestParam String musicname,@RequestParam String singer,@RequestParam String publisher,@RequestParam String briefintro,
                              @RequestParam String pubdate,@RequestParam String musictype) {
        musicService.addnewmusic(musicname,singer,publisher,briefintro,pubdate,musictype);
        return "操作成功";
    }

    /*跳转音乐详情*/
    @RequestMapping(value = "/musicdetails")
    public String findallmusic1(int musicid, Map<String,Object> result) {
        MusicEntity musicEntity = musicService.findmusicById(musicid);

        result.put("musicEntity",musicEntity);

        if(musicEntity == null){
            return null;
        }else {
            return "/music/musicdetails";
        }
    }

    /*搜索被推荐的音乐*/
    @RequestMapping(value = "/findmusicrecommend")
    @ResponseBody
    public Map<String, Object> findmusicrecommend(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                                 @RequestParam(defaultValue = "2") Integer pageSize) {
        try {
            List<MusicEntity> musicEntity = musicService.findmusicrecommend();
            PageHelper.startPage(pageNum, pageSize);
            PageInfo pageInfo = new PageInfo(musicService.findmusicrecommend());

            //直接返回json字符串
            Map<String, Object> music = new HashMap<>();

            music.put("musicNum",musicEntity.size());
            music.put("musiclist",pageInfo.getList());

            return music;
        } catch (Exception e) {
            return null;
        }
    }

    /*更新音乐*/
    @RequestMapping(value = "/updatemusic")
    @ResponseBody
    public String updatemusic(@RequestParam int musicid,@RequestParam String musicname,@RequestParam String singer,@RequestParam String publisher,
                              @RequestParam String pubdate,@RequestParam String briefintro) {
        int result =  musicService.updatemusic(musicid,musicname,singer,publisher,pubdate,briefintro);

        if (result > 0 ) {
            return "操作成功";
        } else {
            return "操作失败";
        }
    }


    /*删除音乐*/
    @RequestMapping(value = "/delmusic")
    @ResponseBody
    public String delmusic(@RequestParam int musicid) {
        int result =  musicService.delmusic(musicid);

        if (result > 0 ) {
            return "操作成功";
        } else {
            return "操作失败";
        }
    }

    /*更新音乐的封面*/
    @RequestMapping(value = "/upMusicprc")
    @ResponseBody
    public Map<String,Object> upMusicprc(HttpSession session, MultipartFile file, @RequestParam int musicid) {

        Map<String,Object> map = new HashMap<>();

        if (file != null){
            try {
                String filepath = "D:\\java应用技术课设\\JavaEECS\\CampusRecreation\\src\\main\\resources\\static\\prc\\musicprc\\"+musicid+".jpg";

                file.transferTo(Paths.get(filepath));

                map.put("uploaded","上传成功");

                /*更新图书封面信息*/
                musicService.updateMusicprc(musicid);

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
