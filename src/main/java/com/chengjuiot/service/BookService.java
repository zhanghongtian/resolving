package com.chengjuiot.service;

import com.chengjuiot.domain.Book;
import com.chengjuiot.domain.BookLog;

import java.util.List;



public interface BookService {
//	BookLog get(Integer bookLogId);
	List<Book>getAll();
//	List<BookLog> getByBookId(Integer bookid);
//	BookLog update(BookLog b);
//	List<BookLog> getFlag(Integer flag, Integer bookid);
    Book saveBook(Book b);
    BookLog saveBookLog(BookLog b);
    void saveBookAndBookLog() throws Exception;
//	BookLog getBookLogByIdAndBookId(Integer bookid, Integer bookLogid);
//	List<Book> getBookByName(String bookName);
}
