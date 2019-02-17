package com.application.entity;

import com.application.Entity.Blog;

public class Blogs {
	
	private Blog blog;
	private long blogTypeId;
	
	private long pictureId;
	
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
	
	
	

}
