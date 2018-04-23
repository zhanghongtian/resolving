package com.chengjuiot.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.filechooser.FileFilter;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FileResolvingUtil {
	public String SSSS;

	/**
	 * 
	 * 对文件进行处理(由于文件的内容格式不同)
	 * 
	 * @param mkDirectoryPath 创健一个新的目录存放处理过的文件
	 * @param inFilePath 读取的文件位置（我们未文件的位置）
	 * @param outFilePath 放文件的位置（我们处理的文件放到新建的目录下）
	 * @throws IOException
	 */
	public void fileProcessing(String mkDirectoryPath, String inFilePath, String outFilePath, String replaceStr) throws IOException {
		File inFile = new File(inFilePath);
		File mkDirectory = new File(mkDirectoryPath);
		File outFile = new File(outFilePath);
		if (!mkDirectory.exists()) {
			mkDirectory.mkdirs();
		}
		if (outFile.exists()) {
			boolean a = outFile.delete();
			if(a==false) {
				throw new RuntimeException("文件删除失败");
			}
		}
		BufferedReader br = null;
		PrintWriter pw = null;
		try {
			/// Users/apple/eclipse-workspace/book_demo/target/classes/books-demo/book/7/Text/Section0006.xhtml
			FileInputStream fis = new FileInputStream(inFile);
			InputStreamReader isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);

			FileOutputStream fos = new FileOutputStream(outFile, true);
			OutputStreamWriter ops = new OutputStreamWriter(fos);
			pw = new PrintWriter(ops);
			System.out.println("sss");
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = br.readLine()) != null) {
				if (tempString != "") {
					if (!tempString.endsWith("/DTD/xhtml11.dtd\">")) {
						if (!tempString.startsWith("<!DOCTYPE")) {
							if (tempString.contains("../")) {
								//String rep = host + "/img/" + bookLocation + "/";
								String s = tempString.replace("../", replaceStr);
								pw.println(s);
							} else {
								pw.println(tempString);
							}
							pw.flush();
						}
					}

				}
			}
		} catch (Exception e) {
			close(br, pw);
			e.printStackTrace();
		} finally {
			close(br, pw);
		}
	}

	public void close(Reader r, Writer w) throws IOException {
		if (r != null) {
			r.close();
		}
		if (w != null) {
			w.close();
		}
	}

	public static void main(String[] args) {
//		List<Map<String, String>> s = readBook("books-all.json");
//		for (Map<String, String> m : s) {
//			System.out.println(m.toString());
//		}
//        File file = new File("/Users/apple/DeskTop/无效码");
////        deleteDir(file);
//		List<Map<String,String>> s = readBook("/Users/apple/Downloads/","books-all.json");
//		System.out.println(s);
		// String s = getBookLoaction("/index/33.xml");
		// System.out.println(s);
		// List<String> list = getFilesForDirectory(s);
		// System.out.println(list.size());
		// getTextPath("33","Text");
		// List<String> list = getFilesForDirectory(SSSS);
		// System.out.println(list);
	}

	/**
	 * 获取所有书对应的目录
	 * 
	 * @param str
	 * @return
	 */
	public String getBookLoaction(String str) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		String s = "/index/";
		String s1 = ".";
		int i = str.indexOf(s) + 7;
		int a = str.indexOf(s1);
		return str.substring(i, a);
	}

	/**
	 * 获取我们要的文本的目录
	 * 
	 * @param baseDirectoryName
	 * @param directoryName
	 */
	public void getTextPath(String ZHANGJIE_PATH,String baseDirectoryName, String directoryName) {
		String ss1 = ZHANGJIE_PATH + baseDirectoryName;
		File f = new File(ss1);
		grt(f, directoryName);
	}

	/**
	 * 
	 * 跟踪查询我们要的目录地址
	 * 
	 * @param f
	 * @param directoryName
	 */
	public void grt(File f, String directoryName) {
		String ss = "";
		if (f.exists()) {
			File[] file = f.listFiles();
			for (File f1 : file) {
				if (f1.isDirectory()) {
					String name = f1.getName();
					System.out.println(name);
					if (directoryName.equals(name)) {
						System.out.println("1");
						SSSS = f1.getAbsolutePath();
						break;
					} else {
						System.out.println("2");
						grt(f1, directoryName);
					}
				}
			}
		}
	}

	/***
	 * 获取Text目录下的所有章节文件
	 * 
	 * @param directoryName
	 * @return
	 */
	public List<String> getFilesForDirectory(String directoryName) {
		String releasePath = directoryName;
		File file = new File(releasePath);
		List<String> list = new ArrayList<String>();
		if (file.exists()) {
			File[] fs = file.listFiles();
			if (fs.length <= 0) {
				return null;
			}
			for (File f : fs) {
				if (f.isFile()) {
					list.add(f.getName());
				}
			}
		} else {
			return null;
		}
		return list;
	}

	public static void deleteDir(String filePath){
	    File file = new File(filePath);
	    if(file.exists()){
	        if(file.isDirectory()){
	            File[] files = file.listFiles();
	            if(files.length<=0){
                    file.delete();
                }else {
                    for (File f : files) {
                        f.delete();
                    }
                    file.delete();
                }
            }
        }
    }

	/**
	 * 
	 * 获取每记录每一本书的json
	 * 
	 * @param fileName
	 * @return
	 */
	public static List<Map<String, String>> readBook(String MULU_PATH,String fileName) {
//		System.out.println("ssssssssss"+MULU_PATH);
		String releasePath = MULU_PATH + fileName;
		File file = new File(releasePath);
		try {
			FileInputStream fis = new FileInputStream(file);
			int len = 0;
			String jsonStr = null;
			byte[] buf = new byte[10240];
			while ((len = fis.read(buf)) != -1) {
				jsonStr = new String(buf);
			}
			JSONObject json = JSONObject.fromObject(jsonStr);
			JSONArray jsonArray = json.getJSONArray("data");
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			for (Object s : jsonArray) {
				Map<String, String> map = (Map<String, String>) s;
				list.add(map);
			}
			fis.close();
			return list;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
