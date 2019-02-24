package com.application.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VisitorRecord {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//访客的id
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
	
	//访客访问的次数
	private long visitNum=0;
	
	//访客的状态,1表示管理员，2表示普通人员，3表示黑名单
	private int status;

	public VisitorRecord() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}

	public long getVisitNum() {
		return visitNum;
	}

	public void setVisitNum(long visitNum) {
		this.visitNum = visitNum;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
