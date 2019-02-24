package com.application.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
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

import com.application.Entity.Blog;
import com.application.Entity.Visit;
import com.application.Entity.Visitor;
import com.application.Entity.VisitorRecord;
import com.application.service.BlogService;
import com.application.service.VisitorRecordService;
import com.application.service.VisitorService;

@Controller
public class VisitorManager {

	@Autowired
	private VisitorService visitorService;
	@Autowired
	private VisitorRecordService visitorRecordService;
	
	@Autowired
	private BlogService blogService;
	
	
	@GetMapping("/visit")
	public String visit()
	{
		return "blogs/visitor/visit";
	}
	
	@GetMapping("/visitTable")
	public String visitTable()
	{
		return "blogs/visitor/visitTable";
	}
	
	@GetMapping("/black")
	public String black()
	{
		return "blogs/visitor/black";
	}
	
	
	//获取记录数，用户数、黑名单数
	@GetMapping("/selectVisitListByStatus")
	@ResponseBody
	public List<Long> selectVisitListByStatus(HttpServletRequest request)
	{
		List<Long> nums=new ArrayList<Long>();
		//普通人员浏览数
		long a=(long) visitorService.getVisitorForListByStatus(1).size();
		System.out.println("a="+a);
		//管理员浏览数
		long b=(long) visitorService.getVisitorForListByStatus(2).size();
		System.out.println("b="+b);
		//黑名单人数
		long c=(long) visitorRecordService.getVisitorRecordForListByStatus(3).size();
		//所有的用户数
		long d=visitorRecordService.getAllRecord().size();
		System.out.println("d="+d);
		nums.add(a+b);
		nums.add(d);
		nums.add(c);
		return nums;
	}
	
	

	
	//
	//根据日期查询，根据快速查询、根据ip进行模糊匹配
	//status： 1 根据ip匹配， 2根据日期匹配   3根据地区进行匹配   4根据浏览器进行匹配   5根据系统进行匹配 
	@PostMapping("/selectLikeVisitListByPage")
	@ResponseBody
	public Page<Visitor> selectLikeVisitListByPage(HttpServletRequest request)
	{
		int status=Integer.parseInt(request.getParameter("status"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		int page=Integer.parseInt(request.getParameter("page"));
		System.out.println("status "+status+" pagesize "+pageSize+" page "+page);
		Sort sort=new Sort(Direction.DESC,"id");
		Pageable pageable=new PageRequest(page-1, pageSize,sort);
		Page<Visitor> visitors=null;
		switch (status) {
		//根据ip进行匹配
		case 1:
			String ip=request.getParameter("ip");
			System.out.println("ip为"+ip);
			if(ip.equals(""))
			{
				Pageable pageable2=new PageRequest(page-1, pageSize);
				visitors=visitorService.getAllVisitors(pageable2);
			}
			else
			{
				visitors=visitorService.getVisitorForPageByIp(ip, pageable);
			}
			break;
		//根据日期查询，获取其实日期和终止日期
		case 2:
			String startTime=request.getParameter("startTime");
			String endTime=request.getParameter("endTime");
			System.out.println("起始日期为："+startTime+"终止日期为："+endTime);
			visitors=visitorService.getVisitorForPageByDate(startTime, endTime, pageable);
			break;
		//根据地区来查询
		case 3:
			String area=request.getParameter("city");
			System.out.println("地区为："+area);
			visitors=visitorService.getVisitorForPageByArea(area, pageable);
			break;
			
		//根据浏览器来查询
		case 4:
			String broswer=request.getParameter("browserType");
			System.out.println("浏览器为："+broswer);
			visitors=visitorService.getVisitorForPageByBrowser(broswer, pageable);
			break;
			
		//根据系统来查询
		case 5:
			String system=request.getParameter("platformType");
			System.out.println("系统为："+system);
			visitors=visitorService.getVisitorForPageBySystem(system, pageable);
			
			break;
		default:
			break;
		}
		return visitors;
	}
	
	
	
	//操作日志页面
	//根据不同的情况进行查询，查询指定页的内容
	@PostMapping("/selectLogList")
	@ResponseBody
	public Page<Visitor> selectLogList(HttpServletRequest request)
	{
		int page=Integer.parseInt(request.getParameter("page"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		String keyWord=request.getParameter("keyWord");
		int type=Integer.parseInt(request.getParameter("type"));
		System.out.println("page:"+page+" pageSize "+pageSize+" keyWord: "+keyWord+" type: "+type);
		Sort sort=new Sort(Direction.DESC,"id");
		Pageable pageable=new PageRequest(page-1, pageSize, sort);
		Page<Visitor> visitors=null;
		switch (type) {
		
		//查询所有的访客
		case 0:
			String startTime=request.getParameter("startTime");
			String endTime=request.getParameter("endTime");
			System.out.println("起始日期为："+startTime+"终止日期为："+endTime);
			visitors=visitorService.getVisitorForSpecificPageByDate(startTime, endTime, pageable);
			break;
		//根据访客身份状态进行查询
		//根据管理员进行查询
		case 1:
			visitors=visitorService.getVisitorByStatusForSpecificPage(1, pageable);
			break;
		//根据普通用户进行查询
		case 2:
			visitors=visitorService.getVisitorByStatusForSpecificPage(2, pageable);
			break;
		
		//根据访客身份状态和操作类型查询
		//根据普通用户查看博客来查询
		case 3:
			visitors=visitorService.getVisitorByStatusAndOpeObjectForSpecificPage(2, 5, pageable);
			break;
			
		//根据普通用户关键字搜索博客查询
		case 4:
			visitors=visitorService.getVisitorByStatusAndOpeObjectForSpecificPage(2, 6, pageable);
			break;
		
		//根据普通用户搜索资源进行查询
		case 5:
			visitors=visitorService.getVisitorByStatusAndOpeObjectForSpecificPage(2, 7, pageable);
			break;
		
		//根据管理员博客增删改进行查询
		case 6:
			visitors=visitorService.getVisitorByStatusAndOpeObjectForSpecificPage(1, 1, pageable);
			break;
			
		//根据管理员博客类别增删改进行查询
		case 7:
			visitors=visitorService.getVisitorByStatusAndOpeObjectForSpecificPage(1, 2, pageable);
			break;
			
		//根据管理员资源增删改进行查询
		case 8:
			visitors=visitorService.getVisitorByStatusAndOpeObjectForSpecificPage(1, 3, pageable);
			break;
			
		//根据管理员黑名单增删进行查询
		case 9:
			visitors=visitorService.getVisitorByStatusAndOpeObjectForSpecificPage(1, 4, pageable);
			break;
		
		
		//根据ip进行查询
		case 10:
			
			visitors=visitorService.getVisitorByIpForSpecificPage(keyWord, pageable);
			break;

		default:
			break;
		}
		
		return visitors;
	}
	
	//获取今日访客和所有访客数量
	@GetMapping("/selectVisitForToday")
	@ResponseBody
	public List<Long> selectVisitForToday()
	{
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		String today=simpleDateFormat.format(date);
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(calendar.DATE, date.getDate()-1);
		String yesterday=simpleDateFormat.format(calendar.getTime());
		calendar.set(calendar.DATE, date.getDate()+1);
		String tomorrow=simpleDateFormat.format(calendar.getTime());
		long now=visitorService.getVisitorListByDate(today, tomorrow);
		long yes=visitorService.getVisitorListByDate(yesterday, today);
		long all=visitorService.getAllVisitorCount();
		List<Long> nums=new ArrayList<Long>();
		nums.add(now);
		nums.add(yes);
		nums.add(all);
		
		
		return nums;
	}
	
	
	//获取指定日期的访客数量
	@GetMapping("/selectVisitByDate")
	@ResponseBody
	public Visit selectVisitByDate(HttpServletRequest request) throws ParseException
	{
		String  type=request.getParameter("type");
		List<String> days=new ArrayList<String>();
		List<Long> counts=new ArrayList<Long>(); 
		Date date=new Date();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		long nd=1000*24*60*60;
		Visit visit=new Visit();
		SimpleDateFormat simpleDateFormat=null;
		System.out.println("type: "+type);
		if(type.equals("date"))
		{
			simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
			String status=request.getParameter("status");
			long num=0;
			if(status.equals("date"))
			{
				String startTime=request.getParameter("startTime");
				String endTime=request.getParameter("endTime");
				Date start=simpleDateFormat.parse(startTime);
				Date end=simpleDateFormat.parse(endTime);
				 num=(end.getTime()-start.getTime())/nd;
			}
			else
			{
				num=Long.parseLong(request.getParameter("date"))-1;
			}
			
			System.out.println("相隔"+num+"天");
			for(long i=num;i>0;i--)
			{
				calendar.set(calendar.DATE, (int) (date.getDate()-i));
				days.add(simpleDateFormat.format(calendar.getTime()));
			}
			days.add(simpleDateFormat.format(date));
			calendar.set(calendar.DATE, date.getDate()+1);
			days.add(simpleDateFormat.format(calendar.getTime()));
			
			for(long i=0;i<=num;i++)
			{
				counts.add(visitorService.getVisitorListByDate(days.get((int) i), days.get((int) (i+1))));
			}	
			visit.setCount(counts);
			visit.setDays(days);
			visit.setNum(num+1);
			return visit;
		}
		
		else if(type.equals("month"))
		{
			int monthNum=Integer.parseInt(request.getParameter("num"));
			System.out.println("月份数为：" +monthNum);
			simpleDateFormat=new SimpleDateFormat("yyyy-MM");
			Date date2=new Date();
			calendar.set(calendar.MONTH, date.getMonth()-monthNum+1);
			date2=calendar.getTime();
			calendar.setTime(date2);
			
			for(long i=1;i<=monthNum+1;i++)
			{
				System.out.println(simpleDateFormat.format(calendar.getTime()));
				days.add(simpleDateFormat.format(calendar.getTime()));
				calendar.set(calendar.MONTH, (int) (date2.getMonth()+1));
				
				date2=calendar.getTime();
				calendar.setTime(date2);
			}
			
			for(long i=0;i<monthNum;i++)
			{
				counts.add(visitorService.getVisitorListByDate(days.get((int) i), days.get((int) (i+1))));
			}
			visit.setCount(counts);
			visit.setDays(days);
			visit.setNum(monthNum+1);
			
			return visit;
		}
		else if(type.equals("year"))
		{
			int yearNum=Integer.parseInt(request.getParameter("num"));
			System.out.println("年数为："+yearNum);
			simpleDateFormat=new SimpleDateFormat("yyyy");
			Date date2=new Date();
			calendar.add(calendar.YEAR, -yearNum+1);
			date2=calendar.getTime();
			calendar.setTime(date2);
			days.add(simpleDateFormat.format(calendar.getTime()));
			for(long i=1;i<=yearNum;i++)
			{
				System.out.println(simpleDateFormat.format(calendar.getTime()));
				
				calendar.add(calendar.YEAR, 1);;
				
				date2=calendar.getTime();
				calendar.setTime(date2);
				days.add(simpleDateFormat.format(calendar.getTime()));
				
			
			}
			for(long i=0;i<yearNum;i++)
			{
				counts.add(visitorService.getVisitorListByDate(days.get((int) i), days.get((int) (i+1))));
			}
			visit.setCount(counts);
			visit.setDays(days);
			visit.setNum(yearNum+1);
			
			return visit;
		}
		
		return null;
	}
	
	//根据访客状态和时间查询访客数量
	@GetMapping("/selectUserLogByDate")
	@ResponseBody
	public Visit selectUserLogByDate(HttpServletRequest request) throws ParseException
	{
		String userType=request.getParameter("userType");
		List<String> days=new ArrayList<String>();
		List<Long> counts=new ArrayList<Long>(); 
		Date date=new Date();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		long nd=1000*24*60*60;
		SimpleDateFormat simpleDateFormat=null;
		long num=0;
		Visit visit=new Visit();
		if(userType.equals("普通用户"))
		{
			simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
			String startTime=request.getParameter("startTime");
			String endTime=request.getParameter("endTime");
			Date start=simpleDateFormat.parse(startTime);
			Date end=simpleDateFormat.parse(endTime);
			 num=(end.getTime()-start.getTime())/nd;
			 for(long i=num;i>0;i--)
				{
					calendar.set(calendar.DATE, (int) (date.getDate()-i));
					days.add(simpleDateFormat.format(calendar.getTime()));
				}
				days.add(simpleDateFormat.format(date));
				calendar.set(calendar.DATE, date.getDate()+1);
				days.add(simpleDateFormat.format(calendar.getTime()));
				
				for(long i=0;i<=num;i++)
				{
					counts.add(visitorService.getVisitorListByDate(days.get((int) i), days.get((int) (i+1))));
				}	
				visit.setCount(counts);
				visit.setDays(days);
				visit.setNum(num+1);
				return visit;
		}
		return null;
		
	}
	
	
}
