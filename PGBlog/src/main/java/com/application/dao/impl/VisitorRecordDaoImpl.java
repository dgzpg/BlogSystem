package com.application.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.application.Entity.VisitorRecord;
import com.application.dao.VisitorDao;
import com.application.dao.VisitorRecordDao;
import com.application.repository.VisitorRecordRepository;

@Service
public class VisitorRecordDaoImpl implements VisitorRecordDao{

	@Autowired
	private VisitorRecordRepository visitorRecordRepository;
	//保存访客记录
	public boolean save(VisitorRecord visitorRecord) {
		try {
			visitorRecordRepository.saveAndFlush(visitorRecord);
			return true;
		} catch (Exception e) 
		{
			return false;
		}
		
	}
	
	//根据ip查找访客
	public List<VisitorRecord> getRecordByIp(String ip)
	{
		return visitorRecordRepository.getRecordByIp(ip);
	}
	
	//根据id获取访客
	public VisitorRecord getRecordById(long id)
	{
		return visitorRecordRepository.findById(id).get();
	}

	//修改记录
	public boolean editRecord(VisitorRecord visitorRecord)
	{
		VisitorRecord visitorRecord2=visitorRecordRepository.findById(visitorRecord.getId()).get();
		visitorRecord2=visitorRecord;
		try {
			visitorRecordRepository.saveAndFlush(visitorRecord2);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	
	
	
			
	//根据状态获取所有的访客并进行分页
	public Page<VisitorRecord> getAllVisitorRecords(Pageable pageable)
	{
		return visitorRecordRepository.getAllVisitorRecords(pageable);
	}
	
	//根据ip进行模糊匹配
	public Page<VisitorRecord> getVisitorRecordForPageByIp(String ip,Pageable pageable)
	{
		return visitorRecordRepository.getVisitorRecordForPageByIp(ip, pageable);
	}
			
	//根据日期进行查询
	public Page<VisitorRecord> getVisitorRecordForPageByDate(String startTime,String endTime,Pageable pageable)
	{
		return visitorRecordRepository.getVisitorRecordForPageByDate(startTime, endTime, pageable);
	}
			
	//根据地区进行模糊匹配
	public Page<VisitorRecord> getVisitorRecordForPageByArea(String area,Pageable pageable)
	{
		return visitorRecordRepository.getVisitorRecordForPageByArea(area,pageable);
	}
			
	//根据浏览器进行匹配
	public Page<VisitorRecord> getVisitorRecordForPageByBrowser(String broswer, Pageable pageable)
	{
		return visitorRecordRepository.getVisitorRecordForPageByBrowser(broswer, pageable);
	}
			
	//根据系统进行匹配
	public Page<VisitorRecord> getVisitorRecordForPageBySystem(String system,Pageable pageable)
	{
		return visitorRecordRepository.getVisitorRecordForPageBySystem(system, pageable);
	}
	
	//根据ip进行模糊匹配黑名单
	public Page<VisitorRecord> getBlackRecordForPageByIp(String ip,int status,Pageable pageable)
	{
		return visitorRecordRepository.getBlackRecordForPageByIp(ip, status, pageable);
	}
					
	//根据日期进行查询黑名单
	public Page<VisitorRecord> getBlackRecordForPageByDate(String startTime,String endTime,int status,Pageable pageable)
	{
		return visitorRecordRepository.getBlackRecordForPageByDate(startTime, endTime, status, pageable);
	}
					
	//根据地区进行模糊匹配黑名单
	public Page<VisitorRecord> getBlackRecordForPageByArea(String area,int status,Pageable pageable)
	{
		return visitorRecordRepository.getBlackRecordForPageByArea(area, status, pageable);
	}
				
	//根据浏览器进行匹配黑名单
	public Page<VisitorRecord> getBlackRecordForPageByBrowser(String broswer,int status, Pageable pageable)
	{
		return visitorRecordRepository.getBlackRecordForPageByBrowser(broswer, status, pageable);
	}
					
	//根据系统进行匹配黑名单
	public Page<VisitorRecord> getBlackRecordForPageBySystem(String system,int status,Pageable pageable)
	{
		return visitorRecordRepository.getBlackRecordForPageBySystem(system, status, pageable);
	}
	
	
	
	
			
	//根据访客状态获取访客
	public List<VisitorRecord> getVisitorRecordForListByStatus(int status)
	{
		return visitorRecordRepository.getVisitorRecordForListByStatus(status);
	}
	
	//根据不同的状态获取所有的访客并进行分页
	public Page<VisitorRecord > getVisitorByStatusForPage(int status,Pageable pageable)
	{
		return visitorRecordRepository.getVisitorByStatusForPage(status, pageable);
	}

	//获取所有的访客，不分页
	public List<VisitorRecord> getAllRecord() {
		// TODO Auto-generated method stub
		return visitorRecordRepository.getAllRecord();
	}

	
	

}
