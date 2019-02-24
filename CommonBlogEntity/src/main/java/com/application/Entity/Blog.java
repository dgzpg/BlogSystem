package com.application.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.websocket.ClientEndpoint;





@Entity
@Table(name="blog")
public class Blog {

	@Id
	@Column(name="blog_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long blogid;
	
	//博客标题
	private String title;
	
	//类型类中设置一对多的关系
	//博客类型
	private String type;
	
	//博客关键字
	private String  keyWord;
	
	//博客浏览量
	private long pageView=0;
	
	//博客推荐
	//1不推荐    2推荐
	private int recommend=1;
	
	//博客状态,1表示发表，2表示垃圾箱，-1表示草稿
	private int  status;
	
	//博客发表时间
	private String publishedTime;
	
	//博客是否置顶,1不置顶，2置顶
	private int top=1;
	
	//博客摘要，简介
	private String introduction;
	
	//博客封面，保存封面的相对路径
	private String picturePath;
	
	//博客内容
	@Lob
	@Column(columnDefinition="TEXT")
	private String content;
	
	//博客评论id
	@OneToMany(targetEntity=Comment.class)
	@JoinColumn(name="blog_id",referencedColumnName="blog_id")
	private List<Long>commentId=new ArrayList<Long>();
	
	
	//博客数
	private long comment_num=0;


	
	
	
	public Blog() {
		super();
	}




	public Blog(long id, String title, String type, String keyWord, long pageView, int recommend, int status,
			String publishedTime, int top, String introduction, String picturePath, String content,
			long comment_num) {
		super();
		this.blogid = id;
		this.title = title;
		this.type = type;
		this.keyWord = keyWord;
		this.pageView = pageView;
		this.recommend = recommend;
		this.status = status;
		this.publishedTime = publishedTime;
		this.top = top;
		this.introduction = introduction;
		this.picturePath = picturePath;
		this.content = content;
	
		this.comment_num = comment_num;
		
	}




	






	public long getBlogid() {
		return blogid;
	}




	public void setBlogid(long blogid) {
		this.blogid = blogid;
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getType() {
		return type;
	}




	public void setType(String type) {
		this.type = type;
	}




	public String getKeyWord() {
		return keyWord;
	}




	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}




	public long getPageView() {
		return pageView;
	}




	public void setPageView(long pageView) {
		this.pageView = pageView;
	}




	public int getRecommend() {
		return recommend;
	}




	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}




	public int getStatus() {
		return this.status;
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




	public int getTop() {
		return top;
	}




	public void setTop(int top) {
		this.top = top;
	}




	public String getIntroduction() {
		return introduction;
	}




	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}




	public String getPicturePath() {
		return picturePath;
	}




	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}




	public String getContent() {
		return content;
	}




	public void setContent(String content) {
		this.content = content;
	}


	public long getComment_num() {
		return comment_num;
	}




	public void setComment_num(long comment_num) {
		this.comment_num = comment_num;
	}


	


	public List<Long> getCommentId() {
		return commentId;
	}




	public void setCommentId(List<Long> commentId) {
		this.commentId = commentId;
	}




	@Override
	public String toString() {
		return "Blog [blogId=" + blogid + ", title=" + title + ", type=" + type + ", keyWord=" + keyWord + ", pageView="
				+ pageView + ", recommend=" + recommend + ", status=" + status + ", publishedTime=" + publishedTime
				+ ", top=" + top + ", introduction=" + introduction + ", picturePath=" + picturePath + ", content="
				+ content + ", commentId=" + commentId + ", comment_num=" + comment_num + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
