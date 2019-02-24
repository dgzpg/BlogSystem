package com.application.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.application.Entity.Visitor;
import com.application.Entity.VisitorRecord;
import com.application.annotation.MyLog;
import com.application.service.VisitorRecordService;
import com.application.service.VisitorService;

@Controller
public class VisitorRecordManager {

	@Autowired
	private VisitorService visitorService;
	@Autowired
	private VisitorRecordService visitorRecordService;
	
	
	//黑名单访客查询
	@PostMapping("/selectBlackRecordForPage")
	@ResponseBody
	public Page<VisitorRecord> selectBlackRecordForPage(HttpServletRequest request)
	{
		int status=Integer.parseInt(request.getParameter("status"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		int page=Integer.parseInt(request.getParameter("page"));
		System.out.println("status "+status+" pagesize "+pageSize+" page "+page);
		Sort sort=new Sort(Direction.DESC,"id");
		Pageable pageable=new PageRequest(page-1, pageSize);
		Page<VisitorRecord> visitorRecords=null;
		switch (status) {
		//根据ip进行匹配
		case 1:
			String ip=request.getParameter("ip");
			System.out.println("ip为"+ip);
			if(ip.equals(""))
			{
				visitorRecords=visitorRecordService.getVisitorByStatusForPage(3, pageable);
			}
			else
			{
				visitorRecords=visitorRecordService.getBlackRecordForPageByIp(ip, 3, pageable);
			}
			break;
		//根据日期查询，获取其实日期和终止日期
		case 2:
			String startTime=request.getParameter("startTime");
			String endTime=request.getParameter("endTime");
			System.out.println("起始日期为："+startTime+"终止日期为："+endTime);
			visitorRecords=visitorRecordService.getBlackRecordForPageByDate(startTime, endTime, 3, pageable);
			break;
		//根据地区来查询
		case 3:
			String area=request.getParameter("city");
			System.out.println("地区为："+area);
			visitorRecords=visitorRecordService.getBlackRecordForPageByArea(area, 3, pageable);
			break;
			
		//根据浏览器来查询
		case 4:
			String broswer=request.getParameter("browserType");
			System.out.println("浏览器为："+broswer);
			visitorRecords=visitorRecordService.getBlackRecordForPageByBrowser(broswer, 3, pageable);
			break;
			
		//根据系统来查询
		case 5:
			String system=request.getParameter("platformType");
			System.out.println("系统为："+system);
			visitorRecords=visitorRecordService.getBlackRecordForPageBySystem(system, 3, pageable);
			
			break;
		default:
			break;
		}
		return visitorRecords;
	}
	
	
	
	//所有访客查询
	//根据日期查询，根据快速查询、根据ip进行模糊匹配
	//status： 1 根据ip匹配， 2根据日期匹配   3根据地区进行匹配   4根据浏览器进行匹配   5根据系统进行匹配 
	@PostMapping("/selectVisitRecordListByPage")
	@ResponseBody
	public Page<VisitorRecord> selectLikeVisitListByPage(HttpServletRequest request)
	{
		int status=Integer.parseInt(request.getParameter("status"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		int page=Integer.parseInt(request.getParameter("page"));
		int user=Integer.parseInt(request.getParameter("user"));
		System.out.println("status "+status+" pagesize "+pageSize+" page "+page+" user "+user);
		Sort sort=new Sort(Direction.DESC,"id");
		Pageable pageable=new PageRequest(page-1, pageSize);
		Page<VisitorRecord> visitorRecords=null;
		switch (status) {
		//根据ip进行匹配
		case 1:
			String ip=request.getParameter("ip");
			System.out.println("ip为"+ip);
			if(ip.equals(""))
			{
				visitorRecords=visitorRecordService.getAllVisitorRecords(pageable);
			}
			else
			{
				visitorRecords=visitorRecordService.getVisitorRecordForPageByIp(ip, pageable);
			}
			break;
		//根据日期查询，获取其实日期和终止日期
		case 2:
			String startTime=request.getParameter("startTime");
			String endTime=request.getParameter("endTime");
			System.out.println("起始日期为："+startTime+"终止日期为："+endTime);
			visitorRecords=visitorRecordService.getVisitorRecordForPageByDate(startTime, endTime, pageable);
			break;
		//根据地区来查询
		case 3:
			String area=request.getParameter("city");
			System.out.println("地区为："+area);
			visitorRecords=visitorRecordService.getVisitorRecordForPageByArea(area, pageable);
			break;
			
		//根据浏览器来查询
		case 4:
			String broswer=request.getParameter("browserType");
			System.out.println("浏览器为："+broswer);
			visitorRecords=visitorRecordService.getVisitorRecordForPageByBrowser(broswer, pageable);
			break;
			
		//根据系统来查询
		case 5:
			String system=request.getParameter("platformType");
			System.out.println("系统为："+system);
			visitorRecords=visitorRecordService.getVisitorRecordForPageBySystem(system, pageable);
			
			break;
		default:
			break;
		}
		return visitorRecords;
	}
	
	
	//将访客移至黑名单
	@MyLog(value="新增黑名单")
	@PostMapping(value="addBlackIp")
	@ResponseBody
	public int addBlackIp(HttpServletRequest request)
	{
		long id=Long.parseLong(request.getParameter("id"));
		System.out.println("访客的id为："+id);
		VisitorRecord record=visitorRecordService.getRecordById(id);
		
		if(record.getStatus()==3)
		{
			return 0;
		}
		else
		{
			record.setStatus(3);
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			record.setVisitTime(simpleDateFormat.format(new Date()));
			if(visitorRecordService.editRecord(record))
			{
				return 1;
			}
			else
			{
				return 2;
			}
		}
		
	}
	
	//将访客移除黑名单
	@MyLog(value="删除黑名单")
	@PostMapping("/deleteBlackIp")
	@ResponseBody
	public int deleteBlackIp(HttpServletRequest request)
	{
		long id=Long.parseLong(request.getParameter("id"));
		VisitorRecord record=visitorRecordService.getRecordById(id);
		record.setStatus(1);
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		record.setVisitTime(simpleDateFormat.format(new Date()));
		try {
			visitorRecordService.editRecord(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
	
}
