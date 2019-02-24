package com.application.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long admin_Id;
	
	//管理员账号
	private String  countId;
	
	//管理员密码
	private String  password;
	
	//管理员图像，保存的是相对地址
	private String picturePath;
	
	//管理员状态
	private int status;
	
	
	

	
	
	public Admin() {
		super();
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

	


	public long getAdmin_Id() {
		return admin_Id;
	}



	public void setAdmin_Id(long admin_Id) {
		this.admin_Id = admin_Id;
	}










	public int getStatus() {
		return status;
	}










	public void setStatus(int status) {
		this.status = status;
	}


	
	
	
	
}
