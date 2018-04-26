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
		String getMulu_path  = fileUrlConfig.getMulu_path();
		FileResolvingUtil fileResolvingUtil= new FileResolvingUtil();
		List<Map<String,String>> bookMapList = fileResolvingUtil.readBook(getMulu_path+"/","books-all.json");
		String indexFileName = "/b_content.xhtml";
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
			//变化  要去index中找到书的索引
			String booKIndexAddrss = fileUrlConfig.getIndex_path()+bookLocation+".xml";
			List<String> mei_booKIndexAddrss = GetBookBodyAndTitleUtil.getBookIndexAddress(booKIndexAddrss);
			String mei_Book_zhangjie = mei_booKIndexAddrss.get(0);
			//找到文件前边的目录
			int i = mei_Book_zhangjie.lastIndexOf("/");
			String startStr = mei_Book_zhangjie.substring(0,i);
			String shudezhangjieweizhi = getMulu_path+startStr;
			System.out.println("startStr:::"+startStr);
			//目录文件的地址
			String rep ="/img/" + bookLocation + "/";
			String in_mulu_indexPath = shudezhangjieweizhi+indexFileName;
			String mkDirectoryPath  = shudezhangjieweizhi+fileUrlConfig.getLinshiDir();
			String out_mulu_indexPath = shudezhangjieweizhi+fileUrlConfig.getLinshiDir()+"/"+indexFileName;
			fileResolvingUtil.fileProcessing(mkDirectoryPath,in_mulu_indexPath,out_mulu_indexPath,rep);
			GetIndexListUtil get = new GetIndexListUtil();
			List<NodeValueVO> list = get.getIndexList(out_mulu_indexPath);
			for(NodeValueVO v:list){
				System.out.println("章节名称：：："+v.getNodeText());
				String book_zhangjie_file_name = GetBookBodyAndTitleUtil.captureStr(v.getAttribute(), fileUrlConfig.getLookupPrefix());
				System.out.println("book_zhangjie_file_name:::"+book_zhangjie_file_name);

				String inputPath  = shudezhangjieweizhi+"/"+book_zhangjie_file_name;
				String outputPath = shudezhangjieweizhi+fileUrlConfig.getLinshiDir()+"/"+book_zhangjie_file_name;

				fileResolvingUtil.fileProcessing(mkDirectoryPath,inputPath,outputPath,rep);
				TitleAndBodyVO  t2= GetBookBodyAndTitleUtil.getBookBodyAndTile(outputPath);
				System.out.println("章节的内容：：：："+t2.getBody());
				BookLog bl = new BookLog();
                bl.setBookId(book.getId());
                bl.setLog(v.getNodeText());
                bl.setTitle(t2.getTitle());
                bl.setContent(t2.getBody());
                bl.setFlag(0);
                bl.setCreateTime(new Date());
                bookLogDao.save(bl);
			}

//			for(String mei_Book_zhangjie:mei_booKIndexAddrss){
//				int i = mei_Book_zhangjie.lastIndexOf("/");
//				String rep ="/img/" + bookLocation + "/";
//				String startStr = mei_Book_zhangjie.substring(0,i);
//				System.out.println("startStr:::"+startStr);
//				String endStr = mei_Book_zhangjie.substring(i);
//				String mkDirectoryPath = fileUrlConfig.getMulu_path()+startStr+fileUrlConfig.getLinshiDir();
//				System.out.println("mkDirectoryPath:::"+mkDirectoryPath);
//				String inpath = fileUrlConfig.getMulu_path()+mei_Book_zhangjie;
//				System.out.println("inPath:::"+inpath);
//				String outpath = fileUrlConfig.getMulu_path()+startStr+fileUrlConfig.getLinshiDir()+endStr;
//				System.out.println("outpath:::"+outpath);
//				fileResolvingUtil.fileProcessing(mkDirectoryPath,inpath,outpath,rep);
////				String zhangjie = fileUrlConfig.getMulu_path()+mei_Book_zhangjie;
//				TitleAndBodyVO  t2= GetBookBodyAndTitleUtil.getBookBodyAndTile(outpath);
//				System.out.println(t2.toString());
//				BookLog bl = new BookLog();
//                bl.setBookId(book.getId());
//                bl.setLog(.getNodeText());
//                bl.setTitle(t2.getTitle());
//                bl.setContent(t2.getBody());
//                bl.setFlag(0);
//                bl.setCreateTime(new Date());
//                bookLogDao.save(bl);
//			}

//			fileUtil.getTextPath(fileUrlConfig.getZhangjie_path(),bookLocation, "Text");
//			String path = fileUtil.SSSS;
//			GetIndexListUtil get = new GetIndexListUtil();
//			String mkDirectoryPath = path+"/LoadDirectory/";
//			String rep ="/img/" + bookLocation + "/";
//			String zong_outFileUrl  = mkDirectoryPath+indexFileName;
////			fileUtil.fileProcessing(mkDirectoryPath,path+"/"+indexFileName,zong_outFileUrl,rep);
//			List<NodeValueVO> list = get.getIndexList();
//			int book_zhangjie =0;
//			for(NodeValueVO v:list) {
//                book_zhangjie += 1;
//                String book_zhangjie_file_name = GetBookBodyAndTitleUtil.captureStr(v.getAttribute(), fileUrlConfig.getLookupPrefix());
//                System.out.println("book_zhangjie_file_name:::::::::" + book_zhangjie_file_name + " 章节名称：：" + v.getNodeText());
//                String inFilePath = path + "/" + book_zhangjie_file_name;
//                String outFilePath = path + "/LoadDirectory/" + book_zhangjie_file_name;
//                fileUtil.fileProcessing(mkDirectoryPath, inFilePath, outFilePath, rep);
//                TitleAndBodyVO t = GetBookBodyAndTitleUtil.getBookBodyAndTile(outFilePath);
//                System.out.println("第 " + book_index + "本书，    第 " + book_zhangjie + "章节  标题：：：" + t.getTitle());
//                /*添加书的章节到t_book_log表中的信息*/
//                BookLog bl = new BookLog();
//                bl.setBookId(book.getId());
//                bl.setLog(v.getNodeText());
//                bl.setTitle(t.getTitle());
//                bl.setContent(t.getBody());
//                bl.setFlag(0);
//                bl.setCreateTime(new Date());
//                bookLogDao.save(bl);
//            }
//            FileResolvingUtil.deleteDir(mkDirectoryPath);
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
