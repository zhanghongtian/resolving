package com.chengjuiot;

import com.chengjuiot.domain.Book;
import com.chengjuiot.service.BookService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class Test {

    @Autowired
    BookService bookService;

    @org.junit.Test
    public void Test0(){
       List<Book> list =  bookService.getAll();
       System.out.println(list);
    }

    @org.junit.Test
    public void Test1() throws Exception {
        bookService.saveBookAndBookLog();
    }
}
