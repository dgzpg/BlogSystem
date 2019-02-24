package com.application.dao;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.application.Entity.Visitor;


public interface VisitorDao {
	
	//保存访客记录
	public  boolean save(Visitor visitor);
	
	//获取所有的访客
	public Page<Visitor> getAllVisitors(Pageable pageable);
	
	//获取所有访客的数量
	public long getAllVisitorCount();
	
	//根据ip进行模糊匹配
	public Page<Visitor> getVisitorForPageByIp(String ip,Pageable pageable);
	
	//根据日期进行查询，返回所有的内容
	public Page<Visitor> getVisitorForPageByDate(String startTime,String endTime,Pageable pageable);
	
	//根据日期进行查询，返回的是具体某页的内容
	public Page<Visitor> getVisitorForSpecificPageByDate(String startTime,String endTime,Pageable pageable);
	
	//根据日期进行查询，返回的是相应时间段的所有访客的数量 
	public long getVisitorListByDate(String startDate,String endDate);
	
	//根据地区进行模糊匹配
	public Page<Visitor> getVisitorForPageByArea(String area,Pageable pageable);
	
	//根据浏览器进行匹配
	public Page<Visitor> getVisitorForPageByBrowser(String broswer, Pageable pageable);
	
	//根据系统进行匹配
	public Page<Visitor> getVisitorForPageBySystem(String system,Pageable pageable);
	
	//根据访客状态获取所有访客
	public List<Visitor> getVisitorForListByStatus(int status);
	
	//根据访客的身份状态（管理员，普通用户）进行查询，返回的是具体的某页结果
	public Page<Visitor> getVisitorByStatusForSpecificPage(int status,Pageable pageable);
		
	//根据访客的身份状态以及操作的类别进行查询，返回的是具体某页的结果
	public Page<Visitor> getVisitorByStatusAndOpeObjectForSpecificPage(int status,int opeObject,Pageable pageable);
		
	//根据ip进行查询，返回的是具体某页的结果
	public Page<Visitor> getVisitorByIpForSpecificPage(String ip,Pageable pageable);

}
