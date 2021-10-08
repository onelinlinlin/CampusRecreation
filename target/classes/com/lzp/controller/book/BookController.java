package com.lzp.controller.book;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzp.entity.BookEntity;
import com.lzp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/findallbook")
    @ResponseBody
    public Map<String, Object> findallbook(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                          @RequestParam(defaultValue = "2") Integer pageSize) {
        try {
            List<BookEntity> bookEntity = bookService.findallbook();
            PageHelper.startPage(pageNum, pageSize);
            PageInfo pageInfo = new PageInfo(bookService.findallbook());

            //直接返回json字符串
            Map<String, Object> book = new HashMap<>();

            book.put("bookNum",bookEntity.size());
            book.put("booklist",pageInfo.getList());

            return book;
        } catch (Exception e) {
            return null;
        }
    }

    /*寻找所有书籍*/
    @RequestMapping(value = "/findallbook2")
    @ResponseBody
    public List<BookEntity> findallbook2() {
        try {
            List<BookEntity> bookEntity = bookService.findallbook();
            return bookEntity;
        } catch (Exception e) {
            return null;
        }
    }

    /*设置书籍推荐*/
    @RequestMapping(value = "/setBookrecommend")
    @ResponseBody
    public String setBookrecommend(@RequestParam String recommend,@RequestParam int bookid) {
        bookService.setBookrecommend(recommend,bookid);
        return "操作成功";
    }

    /*添加新的书籍*/
    @RequestMapping(value = "/addnewbook")
    @ResponseBody
    public String addnewbook(@RequestParam String bookname,@RequestParam String author,@RequestParam String press,@RequestParam String pressdate,
                             @RequestParam String briefintro,@RequestParam String price) {
        bookService.addnewbook(bookname,author,press,pressdate,briefintro,price);
        return "操作成功";
    }

    /*跳转书籍详情*/
    @RequestMapping(value = "/bookdetails")
    public String findallbook1(int bookid, Map<String,Object> result) {
        BookEntity bookEntity = bookService.findBookById(bookid);

        result.put("bookEntity",bookEntity);

        if(bookEntity == null){
            return null;
        }else {
            return "/book/bookdetails";
        }
    }

    /*搜索被推荐的书*/
    @RequestMapping(value = "/findBookrecommend")
    @ResponseBody
    public Map<String, Object> findBookrecommend(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                           @RequestParam(defaultValue = "2") Integer pageSize) {
        try {
            List<BookEntity> bookEntity = bookService.findBookrecommend();
            PageHelper.startPage(pageNum, pageSize);
            PageInfo pageInfo = new PageInfo(bookService.findBookrecommend());

            //直接返回json字符串
            Map<String, Object> book = new HashMap<>();

            book.put("bookNum",bookEntity.size());
            book.put("booklist",pageInfo.getList());

            return book;
        } catch (Exception e) {
            return null;
        }
    }


    /*更新图书*/
    @RequestMapping(value = "/updatebook")
    @ResponseBody
    public String updatebook(@RequestParam int bookid,@RequestParam String bookname,@RequestParam String author,@RequestParam String press,@RequestParam String pressdate,
                             @RequestParam String briefintro,@RequestParam String price) {

        int result =  bookService.updatebook(bookid,bookname,author,press,pressdate,briefintro,price);

        if (result > 0 ) {
            return "操作成功";
        } else {
            return "操作失败";
        }
    }


    /*删除图书*/
    @RequestMapping(value = "/delbook")
    @ResponseBody
    public String delbook(@RequestParam int bookid) {
        int result =  bookService.delbook(bookid);

        if (result > 0 ) {
            return "操作成功";
        } else {
            return "操作失败";
        }
    }

    /*更新图书的封面*/
    @RequestMapping(value = "/upbookprc")
    @ResponseBody
    public Map<String,Object> upbookprc(HttpSession session, MultipartFile file,@RequestParam int bookid) {
        System.out.println(bookid);

        Map<String,Object> map = new HashMap<>();

        if (file != null){
            try {
                String filepath = "D:\\java应用技术课设\\JavaEECS\\CampusRecreation\\src\\main\\resources\\static\\prc\\bookprc\\"+bookid+".jpg";

                file.transferTo(Paths.get(filepath));

                map.put("uploaded","上传成功");

                /*更新图书封面信息*/
                bookService.updateBookprc(bookid);

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
