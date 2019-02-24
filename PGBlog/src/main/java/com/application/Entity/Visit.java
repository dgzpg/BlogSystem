package com.application.Entity;

import java.util.ArrayList;
import java.util.List;

public class Visit
{
	private List<String> days=new ArrayList<String>();
	private List<Long> count=new ArrayList<Long>();
	//天数
	private long num;
	
	public Visit() {
		super();
	}
	public List<String> getDays() {
		return days;
	}
	public void setDays(List<String> days) {
		this.days = days;
	}
	public List<Long> getCount() {
		return count;
	}
	public void setCount(List<Long> count) {
		this.count = count;
	}
	public long getNum() {
		return num;
	}
	public void setNum(long num2) {
		this.num = num2;
	}
	
	
}