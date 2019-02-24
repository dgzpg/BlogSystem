package com.application.Entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class BlogType {

	//类别id
	@Id
	@Column(name="type_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long typeId;
	
	//博客类别名称
	private String type;
	
	//种类博客数量
	private long typeNum=0;
	
	@OneToMany(targetEntity=Blog.class)
	@JoinColumn(name="type_id",referencedColumnName="type_id")
	private List<Blog>blogs=new ArrayList<Blog>();
	
	//创建时间
	private String createTime;

	public BlogType() {
		super();
	}

	public BlogType(long id, String type, long type_num, String createTime) {
		super();
		this.typeId = id;
		this.type = type;
		this.typeNum = type_num;
		this.createTime = createTime;
	}

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getTypeNum() {
		return typeNum;
	}

	public void setTypeNum(long type_num) {
		this.typeNum = type_num;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	
	
	
	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long type_Id) {
		this.typeId = type_Id;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	@Override
	public String toString() {
		return "BlogType [id=" + typeId + ", type=" + type + ", type_num=" + typeNum + ", createTime=" + createTime + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
