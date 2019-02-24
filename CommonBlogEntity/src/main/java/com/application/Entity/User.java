package com.application.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long userId;
	
	//用户名
	private String userName;
	
	//用户账号
	private String countId;
	
	//用户密码
	private String password;
	
	//用户图片
	private String picturePath;

	public User() {
		super();
	}

	public User(long userId, String userName, String countId, String password, String picturePath) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.countId = countId;
		this.password = password;
		this.picturePath = picturePath;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCountId() {
		return countId;
	}

	public void setCountId(String countId) {
		this.countId = countId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", countId=" + countId + ", password=" + password
				+ ", picturePath=" + picturePath + "]";
	}
	
	
	
	
	
	
	
}
