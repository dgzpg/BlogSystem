package com.application.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.application.Entity.Visitor;

import com.application.dao.VisitorDao;

import com.application.service.VisitorService;

@Service
public class VisitorServiceImpl implements VisitorService{

	@Autowired
	private VisitorDao visitorDao;
	
	//保存记录
	public boolean save(Visitor visitor) {
		return visitorDao.save(visitor);
		
	}
	//获取所有的访客
		public Page<Visitor> getAllVisitors(Pageable pageable)
		{
			return visitorDao.getAllVisitors(pageable);
		}
		
		//获取所有访客的数量
		public long getAllVisitorCount()
		{
			return visitorDao.getAllVisitorCount();
		}
			
		//根据ip进行模糊匹配
		public Page<Visitor> getVisitorForPageByIp(String ip,Pageable pageable)
		{
			return visitorDao.getVisitorForPageByIp(ip, pageable);
		}
			
		//根据日期进行查询
		public Page<Visitor> getVisitorForPageByDate(String startTime,String endTime,Pageable pageable)
		{
			return visitorDao.getVisitorForPageByDate(startTime, endTime, pageable);
		}
		
		//根据日期进行查询，返回的是具体某页的内容
		public Page<Visitor> getVisitorForSpecificPageByDate(String startTime,String endTime,Pageable pageable)
		{
			return visitorDao.getVisitorForSpecificPageByDate(startTime, endTime, pageable);
		}
		
		//根据日期进行查询，返回的是相应时间段的所有访客的数量
		public long getVisitorListByDate(String startDate,String endDate)
		{
			return visitorDao.getVisitorListByDate(startDate, endDate);
		}
			
		//根据地区进行模糊匹配
		public Page<Visitor> getVisitorForPageByArea(String area,Pageable pageable)
		{
			return visitorDao.getVisitorForPageByArea(area, pageable);
		}
			
		//根据浏览器进行匹配
		public Page<Visitor> getVisitorForPageByBrowser(String broswer, Pageable pageable)
		{
			return visitorDao.getVisitorForPageByBrowser(broswer, pageable);
		}
			
		//根据系统进行匹配
		public Page<Visitor> getVisitorForPageBySystem(String system,Pageable pageable)
		{
			return visitorDao.getVisitorForPageBySystem(system, pageable);
		}
		
		//根据访客状态获取所有访客
		public List<Visitor> getVisitorForListByStatus(int status)
		{
			return visitorDao.getVisitorForListByStatus(status);
		}
		
		//根据访客的身份状态（管理员，普通用户）进行查询，返回的是具体的某页结果
		public Page<Visitor> getVisitorByStatusForSpecificPage(int status,Pageable pageable)
		{
			return visitorDao.getVisitorByStatusForSpecificPage(status, pageable);
		}
			
		//根据访客的身份状态以及操作的类别进行查询，返回的是具体某页的结果
		public Page<Visitor> getVisitorByStatusAndOpeObjectForSpecificPage(int status,int opeObject,Pageable pageable)
		{
			return visitorDao.getVisitorByStatusAndOpeObjectForSpecificPage(status, opeObject, pageable);
		}
				
		//根据ip进行查询，返回的是具体某页的结果
		public Page<Visitor> getVisitorByIpForSpecificPage(String ip,Pageable pageable)
		{
			return visitorDao.getVisitorByIpForSpecificPage(ip, pageable);
		}
}
