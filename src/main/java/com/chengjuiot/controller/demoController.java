package com.chengjuiot.controller;

import com.chengjuiot.dao.BookDao;
import com.chengjuiot.domain.Book;
import com.chengjuiot.exception.GlobalException;
import com.chengjuiot.result.CodeMsg;
import com.chengjuiot.result.Result;
import com.chengjuiot.service.BookService;
import com.chengjuiot.util.FileUrlConfig;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class demoController {


    @Autowired
    BookDao bookDao;

    @Autowired
    FileUrlConfig fileUrlConfig;

    @Autowired
    BookService bookService;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello spring";
    }




//    2018-07-25
//    @RequestMapping("/saveBookDB")
//    @ResponseBody
//    public void saveBookDB() {
//        try {
//            bookService.saveBookAndBookLog();
//        }catch (Exception e) {
//            throw new GlobalException(new CodeMsg(0,e.getMessage()));
//        }
//    }

    @RequestMapping("/getBook")
    @ResponseBody
    public Result<List<Book>> getBook(){
        List<Book> list = bookDao.findAll();
        return Result.success(list);
    }

    @RequestMapping("/getFileUrl")
    @ResponseBody
    public Result<String> getFileUrl(){
        String str = fileUrlConfig.getMulu_path();
        return Result.success(str);
    }
}
