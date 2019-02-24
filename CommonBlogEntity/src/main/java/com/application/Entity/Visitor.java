package com.application.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Visitor  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	//记录的id
	private long id;
	
	//访客的ip
	private String ip;
	
	//访客的地区
	private String area;
	
	//访客的浏览器
	private String browser;
	
	//访客的系统
	private String system;
	
	//访客的访问时间
	private String visitTime;
	
	//访客的状态,1为管理员，2为普通人员，3为黑名单
	private int status;
	
	//操作对象,1表示博客,2表示博客类型，3表示资源，4表示黑名单
	private int opeObject;
	
	//具体操作
	private String operate;
	
	//参数
	private String params;

	public Visitor() {
		super();
	}



	public long getVid() {
		return id;
	}



	public void setVid(long vid) {
		this.id = vid;
	}



	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getOpeObject() {
		return opeObject;
	}

	public void setOpeObject(int opeObject) {
		this.opeObject = opeObject;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}
	
	
	
	
	
	

}
