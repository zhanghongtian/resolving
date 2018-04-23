package com.chengjuiot.xmlVo;

import java.io.Serializable;

public class TitleAndBodyVO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5932773300899329798L;
	private String title;
    private String body;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "TitleAndBodyVO [title=" + title + ", body=" + body + "]";
	}
	
    
}
