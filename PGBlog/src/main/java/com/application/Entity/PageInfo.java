package com.application.Entity;

import java.util.ArrayList;
import java.util.List;

public class PageInfo {
	
	//博客数组
	private List<Blog> list=new ArrayList<Blog>();
	
	//博客的总数量
	private long total;

	public PageInfo() {
		super();
	}

	public List<Blog> getList() {
		return list;
	}

	public void setList(List<Blog> list) {
		this.list = list;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
	
	

}
