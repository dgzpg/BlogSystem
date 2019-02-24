package com.application.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Resource {

	@Id
	@Column(name="resource_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	//资源名称
	private String name;
	
	//资源内容
	private String content;
	
	//资源状态,1上架，2下架
	private int status;
	
	//资源发表时间
	private String publishedTime;
	
	//资源链接
	private String src;
	
	//资源密码
	private String password;

	public Resource() {
		super();
	}

	public Resource(long id, String name, String content, int status, String publishedTime, String src,
			String password) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
		this.status = status;
		this.publishedTime = publishedTime;
		this.src = src;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPublishedTime() {
		return publishedTime;
	}

	public void setPublishedTime(String publishedTime) {
		this.publishedTime = publishedTime;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Sources [id=" + id + ", name=" + name + ", content=" + content + ", status=" + status
				+ ", publishedTime=" + publishedTime + ", src=" + src + ", password=" + password + "]";
	}
	
	
	

	
}
