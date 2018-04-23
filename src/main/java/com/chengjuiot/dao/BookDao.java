package com.chengjuiot.dao;


import com.chengjuiot.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookDao extends JpaRepository<Book,String>{

    @Query(value = "select * from book",nativeQuery = true)
    List<Book> findAllbook();

}

