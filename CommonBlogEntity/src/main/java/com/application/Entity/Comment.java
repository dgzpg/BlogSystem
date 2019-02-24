package com.application.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comment {

	@Id
	@Column(name="comment_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long comment_Id;
	
	//普通用户id
	private long user_Id;
	
	//评论内容
	private String commentContent;
	
	

	public Comment() {
		super();
	}

	public Comment(long comment_Id, long user_Id, String commentContent) {
		super();
		this.comment_Id = comment_Id;
		this.user_Id = user_Id;
		this.commentContent = commentContent;
	
	}

	public long getComment_Id() {
		return comment_Id;
	}

	public void setComment_Id(long comment_Id) {
		this.comment_Id = comment_Id;
	}

	public long getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(long user_Id) {
		this.user_Id = user_Id;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	
	@Override
	public String toString() {
		return "Comment [comment_Id=" + comment_Id + ", user_Id=" + user_Id + ", commentContent=" + commentContent
				+ "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
