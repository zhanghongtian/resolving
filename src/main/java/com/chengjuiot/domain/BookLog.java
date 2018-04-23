package com.chengjuiot.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="t_book_log")
@Entity
public class BookLog implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1723989912678585784L;


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name = "book_id")
	private Integer bookId;
	@Column
	private String log;
	@Column
	private String title;
	@Column
	private String content;
	@Column
	private Integer flag;
	@Column(name="location")
	private Integer location;
	@Column(name = "ctime")
    private Date createTime;
    @Column(name = "utime")
    private Date updateTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Integer getLocation() {
		return location;
	}
	public void setLocation(Integer location) {
		this.location = location;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "BookLog [id=" + id + ", bookId=" + bookId + ", log=" + log + ", title=" + title + ", content=" + content
				+ ", flag=" + flag + ", location=" + location + ", createTime=" + createTime + ", updateTime="
				+ updateTime + "]";
	}
	
	
	
	
}
