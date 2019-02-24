package com.application.Entity;

public class PublishedDate {
	
	//今日发表博客数
	private long publishedToday;

	//昨日发表博客数
	private long publishedYesterday;
	
	//前天发表博客数
	private long publishedVorgeston;

	public PublishedDate() {
		super();
	}

	public long getPublishedToday() {
		return publishedToday;
	}

	public void setPublishedToday(long publishedToday) {
		this.publishedToday = publishedToday;
	}

	public long getPublishedYesterday() {
		return publishedYesterday;
	}

	public void setPublishedYesterday(long publishedYesterday) {
		this.publishedYesterday = publishedYesterday;
	}

	public long getPublishedVorgeston() {
		return publishedVorgeston;
	}

	public void setPublishedVorgeston(long publishedVorgeston) {
		this.publishedVorgeston = publishedVorgeston;
	}
	
	
	
	
}
