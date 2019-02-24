package com.application.Entity;

import java.io.Serializable;
 

//创建日志的实体，这个实体直接保存到数据库

public class SysLog implements Serializable{

	
	//地区
	private String area;
	
	//浏览器
	private String browser;
	
	//ip
	private String ip;
	
	//操作类型
	private int opeObject;
	
	//操作
	private String operate;
	
	//参数
	private String param;
	
	//状态
	private int status;
	
	//系统
	private String system;

	public SysLog() {
		super();
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}	
	
}
