package com.chengjuiot.util;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.chengjuiot.xmlVo.TitleAndBodyVO;
import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class GetBookBodyAndTitleUtil {

	public static void main(String[] args) throws Exception {
//		String s = captureStr("asdhvasdasd/Text/gvgSeccct.xhtmlsdasssss","/Text");
//		System.out.println(s);
//		List<String> list = getBookIndexAddress("/Users/apple/DeskTop/books-demo/index/7.xml");
//		System.out.println(list);
//		for(String c:list){
//			System.out.println(c);
//		}
		String s = "/Users/apple/DeskTop/books-demo/book/7/Text/Section0001.xhtml";
		Integer i = s.lastIndexOf("/");
		String str = s.substring(i);
		String str2 = s.substring(0,i);
		String outpath = str2+"/LoadDirectory"+str;
		System.out.println(str);
		System.out.println(str2);
		System.out.println(outpath);
		System.out.println(i);
//
//		TitleAndBodyVO t2 = getBookBodyAndTile("/Users/apple/DeskTop/books-demo/book/7/Text/Section0001.xhtml");
//		System.out.println(t2.getBody());
	}
	public static String captureStr(String ss,String lookupPrefix) {
		String prefix = lookupPrefix;
		String suffix = ".xhtml";
		int start=0;
		int end=0;
		if (ss.contains(prefix)) {
			start = ss.indexOf(prefix);
		}
		if(ss.contains(suffix)) {
			end = ss.indexOf(suffix);
		}
		if(end==0&&start!=0){
			return ss.substring(start+5);
		}else if(end!=0&& start!=0) {
			return ss.substring(start+5, end+6);
		}
		return ss;
	}

	/**
	 * 获取文件的xml对象，然后获取对应的根节点root
	 */
	public static TitleAndBodyVO getBookBodyAndTile(String filePath) throws Exception {
		final SAXReader sax = new SAXReader();// 创建一个SAXReader对象
		final File xmlFile = new File(filePath);// 根据指定的路径创建file对象
		final Document document = sax.read(xmlFile);// 获取document对象,如果文档无节点，则会抛出Exception提前结束
		final Element root = document.getRootElement();// 获取根节点
		System.out.println("aaaaaaa");
		return getNodes(root);// 从根节点开始遍历所有节点
	}

	/**
	 * 从指定节点Element node开始,递归遍历其所有子节点
	 */
	private static TitleAndBodyVO getNodes(final Element node) {
		System.out.println("-------开始新节点-------------");
		TitleAndBodyVO t = new TitleAndBodyVO();
		List<Element> list = node.elements();
		for (Element e : list) {
			if ("head".equals(e.getName())) {
				List<Element> elist = e.elements();
				for(Element s:elist) {
					if("title".equals(s.getName())) {
						t.setTitle(s.getTextTrim());
//						System.out.println("title:"+s.getTextTrim());
					}
				}
			}
			if ("body".equals(e.getName())) {
				t.setBody(e.asXML());
			}
		}
		return t;
	}

	/**
	 * 获取文件的xml对象，然后获取对应的根节点root
	 */
	public static List<String> getBookIndexAddress(String filePath) throws Exception {
		final SAXReader sax = new SAXReader();// 创建一个SAXReader对象
		final File xmlFile = new File(filePath);// 根据指定的路径创建file对象
		final Document document = sax.read(xmlFile);// 获取document对象,如果文档无节点，则会抛出Exception提前结束
		final Element root = document.getRootElement();
		System.out.println(root.getName());// 获取根节点
		System.out.println("aaaaaaa");
		return getIndexBook(root);// 从根节点开始遍历所有节点
	}

	/**
	 * 获取书的地址
	 * @param node
	 * @return
	 */
	private static List<String> getIndexBook(final Element node) {
		System.out.println("-------开始新节点-------------");
		List<Element> list = node.elements();
		List<String> str = new ArrayList<>();
		for (Element e : list) {
			if ("xhtml".equals(e.getName())) {
				List<Element> elist = e.elements();
				for(Element s:elist) {
					if("chapters".equals(s.getName())) {
						str.add(s.getTextTrim());
						System.out.println(":"+s.getTextTrim());
					}
				}
			}
		}
		return str;
	}

}
