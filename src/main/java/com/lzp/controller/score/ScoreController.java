package com.lzp.controller.score;

import com.lzp.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    /*关于评分的操作*/
    @RequestMapping(value = "/findscore")
    @ResponseBody
    public String findscore(@RequestParam int score, @RequestParam String type, @RequestParam int typeid, HttpSession session){

        /*判断用户是否登录*/
        if (session.getAttribute("useridsession") != null && session.getAttribute("usernamesession") != null
                && session.getAttribute("userpassswordsession") !=null) {

            /*获取用户id*/
            int userid = (int)session.getAttribute("useridsession");

            /*判断该用户是否已经评价过*/
            System.out.println(scoreService.findscore(userid,type,typeid));
            if (scoreService.findscore(userid,type,typeid) != null) {
                return "该用户已评价";
            } else {

                /*进行评分的更新*/

                /*获得评分*/
                List<Integer> scorelist = scoreService.findscorelist(type,typeid);

                int sum = score;
                for(int i = 0; i < scorelist.size(); ++i){
                    sum += scorelist.get(i);
                }
                //求平均数
                int scoreAVG = (int) sum /(scorelist.size()+1);
                System.out.println(scoreAVG);

                /*执行新数据的score表插入*/
                scoreService.addnewuserscore(scoreAVG,userid,type,typeid);

                /*执行新数据的各种表更新*/
                updatanewtotype(scoreAVG,type,typeid);

                return "评分成功";
            }
        } else {
            return "用户未登录";
        }
    }

    /*将新的评分更新到各种表中*/
    public void updatanewtotype(int scoreAVG, String type, int typeid){

        if (type.equals("book")) {
            scoreService.updatebookescore(scoreAVG,typeid);
        } else if (type.equals("movie")) {
            scoreService.updatemovieescore(scoreAVG,typeid);
        } else if (type.equals("music")) {
            scoreService.updatemusicescore(scoreAVG,typeid);
        }

    }


}
