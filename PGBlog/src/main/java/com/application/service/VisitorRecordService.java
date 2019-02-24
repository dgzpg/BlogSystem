package com.application.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.application.Entity.VisitorRecord;

public interface VisitorRecordService {
	
			//保存访客记录
			public boolean save(VisitorRecord visitorRecord);
		
			//根据ip查找访客
			public List<VisitorRecord> getRecordByIp(String ip);
			
			//根据id获取访客
			public VisitorRecord getRecordById(long id);
			
			//修改记录
			public boolean editRecord(VisitorRecord visitorRecord);
			
			
			
			//根据访客状态获取访客并且分页
			public Page<VisitorRecord> getAllVisitorRecords(Pageable pageable);
			
			//根据ip进行模糊匹配
			public Page<VisitorRecord> getVisitorRecordForPageByIp(String ip,Pageable pageable);
			
			//根据日期进行查询
			public Page<VisitorRecord> getVisitorRecordForPageByDate(String startTime,String endTime,Pageable pageable);
			
			//根据地区进行模糊匹配
			public Page<VisitorRecord> getVisitorRecordForPageByArea(String area,Pageable pageable);
			
			//根据浏览器进行匹配
			public Page<VisitorRecord> getVisitorRecordForPageByBrowser(String broswer, Pageable pageable);
			
			//根据系统进行匹配
			public Page<VisitorRecord> getVisitorRecordForPageBySystem(String system,Pageable pageable);
			
			//根据ip进行模糊匹配黑名单
			public Page<VisitorRecord> getBlackRecordForPageByIp(String ip,int status,Pageable pageable);
					
			//根据日期进行查询黑名单
			public Page<VisitorRecord> getBlackRecordForPageByDate(String startTime,String endTime,int status,Pageable pageable);
					
			//根据地区进行模糊匹配黑名单
			public Page<VisitorRecord> getBlackRecordForPageByArea(String area,int status,Pageable pageable);
					
			//根据浏览器进行匹配黑名单
			public Page<VisitorRecord> getBlackRecordForPageByBrowser(String broswer,int status, Pageable pageable);
					
			//根据系统进行匹配黑名单
			public Page<VisitorRecord> getBlackRecordForPageBySystem(String system,int status,Pageable pageable);
			
			//根据访客状态获取访客
			public List<VisitorRecord> getVisitorRecordForListByStatus(int status);
			
			//根据不同的状态获取所有的访客并进行分页
			public Page<VisitorRecord > getVisitorByStatusForPage(int status,Pageable pageable);
			
			//获取所有的访客
			public List<VisitorRecord> getAllRecord();
}
