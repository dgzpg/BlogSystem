package com.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.application.Entity.VisitorRecord;
import com.application.dao.VisitorRecordDao;
import com.application.repository.VisitorRecordRepository;
import com.application.service.VisitorRecordService;

@Service
public class VisitorRecordServiceImpl implements VisitorRecordService {

		@Autowired
		private VisitorRecordDao visitorRecordDao;
		
		//保存访客记录,若存在该访客即直接修改
		public boolean save(VisitorRecord visitorRecord) {
			if(visitorRecordDao.getRecordByIp(visitorRecord.getIp()).size()==0)
				return visitorRecordDao.save(visitorRecord);
			else
			{
				visitorRecord.setVisitNum(visitorRecordDao.getRecordByIp(visitorRecord.getIp()).get(0).getVisitNum()+1);
				visitorRecord.setId(visitorRecordDao.getRecordByIp(visitorRecord.getIp()).get(0).getId());
				return visitorRecordDao.editRecord(visitorRecord);
			}
		}
		
		//根据ip查找访客
		public List<VisitorRecord> getRecordByIp(String ip)
		{
			return visitorRecordDao.getRecordByIp(ip);
		}
	
		//根据id获取访客
		public VisitorRecord getRecordById(long id)
		{
			return visitorRecordDao.getRecordById(id);
		}
		
		//根据ip进行模糊匹配
		public Page<VisitorRecord> getVisitorRecordForPageByIp(String ip,Pageable pageable)
		{
			return visitorRecordDao.getVisitorRecordForPageByIp(ip,pageable);
		}
				
		//根据日期进行查询
		public Page<VisitorRecord> getVisitorRecordForPageByDate(String startTime,String endTime,Pageable pageable)
		{
			return visitorRecordDao.getVisitorRecordForPageByDate(startTime, endTime, pageable);
		}
				
		//根据地区进行模糊匹配
		public Page<VisitorRecord> getVisitorRecordForPageByArea(String area,Pageable pageable)
		{
			return visitorRecordDao.getVisitorRecordForPageByArea(area, pageable);
		}
				
		//根据浏览器进行匹配
		public Page<VisitorRecord> getVisitorRecordForPageByBrowser(String broswer, Pageable pageable)
		{
			return visitorRecordDao.getVisitorRecordForPageByBrowser(broswer, pageable);
		}
				
		//根据系统进行匹配
		public Page<VisitorRecord> getVisitorRecordForPageBySystem(String system,Pageable pageable)
		{
			return visitorRecordDao.getVisitorRecordForPageBySystem(system, pageable);
		}
				
		//根据访客状态获取所有访客
		public List<VisitorRecord> getVisitorRecordForListByStatus(int status)
		{
			return visitorRecordDao.getVisitorRecordForListByStatus(status);
		}

		//修改访客记录
		public boolean editRecord(VisitorRecord visitorRecord) {
			// TODO Auto-generated method stub
			return visitorRecordDao.editRecord(visitorRecord);
		}

		
		//获取所有的访客
		public Page<VisitorRecord> getAllVisitorRecords(Pageable pageable) {
			// TODO Auto-generated method stub
			return visitorRecordDao.getAllVisitorRecords(pageable);
		}

		//根据ip进行模糊匹配黑名单
		public Page<VisitorRecord> getBlackRecordForPageByIp(String ip,int status,Pageable pageable)
		{
			return visitorRecordDao.getBlackRecordForPageByIp(ip, status, pageable);
		}
						
		//根据日期进行查询黑名单
		public Page<VisitorRecord> getBlackRecordForPageByDate(String startTime,String endTime,int status,Pageable pageable)
		{
			return visitorRecordDao.getBlackRecordForPageByDate(startTime, endTime, status, pageable);
		}
						
		//根据地区进行模糊匹配黑名单
		public Page<VisitorRecord> getBlackRecordForPageByArea(String area,int status,Pageable pageable)
		{
			return visitorRecordDao.getBlackRecordForPageByArea(area, status, pageable);
		}
					
		//根据浏览器进行匹配黑名单
		public Page<VisitorRecord> getBlackRecordForPageByBrowser(String broswer,int status, Pageable pageable)
		{
			return visitorRecordDao.getBlackRecordForPageByBrowser(broswer, status, pageable);
		}
						
		//根据系统进行匹配黑名单
		public Page<VisitorRecord> getBlackRecordForPageBySystem(String system,int status,Pageable pageable)
		{
			return visitorRecordDao.getBlackRecordForPageBySystem(system, status, pageable);
		}
		
		//根据不同的状态获取所有的访客并进行分页
		public Page<VisitorRecord > getVisitorByStatusForPage(int status,Pageable pageable)
		{
			return visitorRecordDao.getVisitorByStatusForPage(status, pageable);
		}


		//获取所有的访客，不分页
		public List<VisitorRecord> getAllRecord() {
			
			return visitorRecordDao.getAllRecord();
		}

		


}
