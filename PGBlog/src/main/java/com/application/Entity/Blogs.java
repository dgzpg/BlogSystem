package com.application.Entity;

import java.util.ArrayList;
import java.util.List;

public class Blogs {
	
	private Blog blog;
	private long blogTypeId;
	
	private long pictureId;
	
	private long num;
	
	private List<String> days=new ArrayList<String>();
	private List<Long> count=new ArrayList<Long>();
	
	public Blogs() {
		super();
	}
	
	public long getPictureId() {
		return pictureId;
	}
	public void setPictureId(long pictureId) {
		this.pictureId = pictureId;
	}
	
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	public long getBlogTypeId() {
		return blogTypeId;
	}
	public void setBlogTypeId(long blogTypeId) {
		this.blogTypeId = blogTypeId;
	}

	public List<String> getDays() {
		return days;
	}

	public void setDays(List<String> days) {
		this.days = days;
	}

	public List<Long> getCount() {
		return count;
	}

	public void setCount(List<Long> count) {
		this.count = count;
	}

	public long getNum() {
		return num;
	}

	public void setNum(long num) {
		this.num = num;
	}
	
	
	

}
