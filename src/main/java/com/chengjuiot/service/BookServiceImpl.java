package com.chengjuiot.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.chengjuiot.dao.BookDao;
import com.chengjuiot.dao.BookLogDao;
import com.chengjuiot.domain.Book;
import com.chengjuiot.domain.BookLog;
import com.chengjuiot.service.BookService;
import com.chengjuiot.util.FileResolvingUtil;
import com.chengjuiot.util.FileUrlConfig;
import com.chengjuiot.util.GetBookBodyAndTitleUtil;
import com.chengjuiot.util.GetIndexListUtil;
import com.chengjuiot.xmlVo.NodeValueVO;
import com.chengjuiot.xmlVo.TitleAndBodyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookDao bookDao;
	@Autowired
	private BookLogDao bookLogDao;
	@Autowired
	FileUrlConfig fileUrlConfig;
	@Override
	public List<Book> getAll() {
		return bookDao.findAll();
	}

//	@Override
//	public List<BookLog> getByBookId(Integer bookid) {
//
//		return bookLogDao.getByBookId(bookid);
//	}
//
//	@Override
//	public BookLog update(BookLog b) {
//
//		return bookLogDao.update(b);
//	}
//
//	@Override
//	public List<BookLog> getFlag(Integer flag,Integer bookid) {
//
//		return bookLogDao.getFlag(flag,bookid);
//	}
//
//	@Override
//	public BookLog get(Integer bookLogId) {
//
//		return bookLogDao.get(bookLogId);
//	}

	@Override
	public Book saveBook(Book b) {
		return bookDao.save(b);
	}

	@Override
	public BookLog saveBookLog(BookLog b) {
		return bookLogDao.save(b);
	}

	@Override
	@Transactional
	public void saveBookAndBookLog() throws Exception {
		FileResolvingUtil fileResolvingUtil= new FileResolvingUtil();
		List<Map<String,String>> bookMapList = fileResolvingUtil.readBook(fileUrlConfig.getMulu_path(),"books-all.json");
		String indexFileName = "b_content.xhtml";
		int book_index = 0;
		for(Map<String,String> bookObject:bookMapList) {
			book_index +=1;
			/*将书的信息添加到t_book*/
			Book b = new Book();
			b.setName(bookObject.get("bookname"));
			b.setCreateTime(new Date());
			Book book = bookDao.save(b);
			String s = bookObject.get("url");
			FileResolvingUtil fileUtil= new FileResolvingUtil();
			String bookLocation = fileUtil.getBookLoaction(s);
			fileUtil.getTextPath(fileUrlConfig.getZhangjie_path(),bookLocation, "Text");
			String path = fileUtil.SSSS;
			GetIndexListUtil get = new GetIndexListUtil();
			String mkDirectoryPath = path+"/LoadDirectory/";
			String rep ="/img/" + bookLocation + "/";
			String zong_outFileUrl  = mkDirectoryPath+indexFileName;
			fileUtil.fileProcessing(mkDirectoryPath,path+"/"+indexFileName,zong_outFileUrl,rep);
			List<NodeValueVO> list = get.getIndexList(zong_outFileUrl);
			int book_zhangjie =0; 
			for(NodeValueVO v:list) {
                book_zhangjie += 1;
                String book_zhangjie_file_name = GetBookBodyAndTitleUtil.captureStr(v.getAttribute(), fileUrlConfig.getLookupPrefix());
                System.out.println("book_zhangjie_file_name:::::::::" + book_zhangjie_file_name + " 章节名称：：" + v.getNodeText());
                String inFilePath = path + "/" + book_zhangjie_file_name;
                String outFilePath = path + "/LoadDirectory/" + book_zhangjie_file_name;
                fileUtil.fileProcessing(mkDirectoryPath, inFilePath, outFilePath, rep);
                TitleAndBodyVO t = GetBookBodyAndTitleUtil.getBookBodyAndTile(outFilePath);
                System.out.println("第 " + book_index + "本书，    第 " + book_zhangjie + "章节  标题：：：" + t.getTitle());
                /*添加书的章节到t_book_log表中的信息*/
                BookLog bl = new BookLog();
                bl.setBookId(book.getId());
                bl.setLog(v.getNodeText());
                bl.setTitle(t.getTitle());
                bl.setContent(t.getBody());
                bl.setFlag(0);
                bl.setCreateTime(new Date());
                bookLogDao.save(bl);
            }
            FileResolvingUtil.deleteDir(mkDirectoryPath);
		}
		
	}

//	@Override
//	public BookLog getBookLogByIdAndBookId(Integer bookid, Integer bookLogid) {
//		return bookLogDao.getBookLogByIdAndBookId(bookid,bookLogid);
//	}
//
//	@Override
//	public List<Book> getBookByName(String bookName) {
//		return bookDao.getBookByName(bookName);
//	}
//

}
