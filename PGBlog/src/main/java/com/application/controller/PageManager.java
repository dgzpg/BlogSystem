package com.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import com.application.Entity.testException;



@Controller
public class PageManager {
	
	@GetMapping("/getAbnormal")
	public void  getAbnormal() throws ClassNotFoundException 
	{
		throw new ClassNotFoundException("类找不到异常");
	}
	
	
	

	//主页
	@GetMapping("/getMainPage")
	public String getMainPage()
	{
		System.out.println("测试mainPage");
		return "/blogs/mainPage";
	}
	
	//操作日志页面跳转
	@GetMapping("/getLogPage")
	public String getLogPage()
	{
		return "/blogs/log/log";
	}
	
	//统计图表页面跳转
	@GetMapping("/getChartPage")
	public String getChart()
	{
		return "/blogs/chart/charts";
	}
	
	//博客管理页面跳转
	@GetMapping("/getBlogManagerPage")
	public String getBlogManagerPage()
	{
		return "/blogs/blog/blogTable";
	}
	
	//写博客页面跳转
	@GetMapping("/getWriteBlogPage")
	public String getWriteBlogPage()
	{
		return "/blogs/blog/addBlog";
	}
	
	//查询博客页面跳转
	@GetMapping("/getSearchBlogPage")
	public String getSearchBlogPage()
	{
		return "/blogs/blog/searchBlog";
	}
	
	//博客类别页面跳转
	@GetMapping("/getBlogTypePage")
	public String getBlogTypePage()
	{
		return "/blogs/blog/blogType";
	}
	
	//资源页面跳转
	@GetMapping("/getSourcesPage")
	public String getSourcesPage()
	{
		System.out.println("资源页面跳转！");
		return "redirect:http://localhost:9020/source";
	}
	
	//访客记录跳转
	@GetMapping("/getVisitorRecordPage")
	public String getVisitorRecordPage()
	{
		return "/blogs/visitor/visit";
	}
	
	//访客统计页面跳转
	@GetMapping("/getVisitorStatisticsPage")
	public String getVisitorStatisticsPage()
	{
		return "/blogs/visitor/visitTable";
	}
	
	//黑名单页面跳转
	@GetMapping("/getBlackListPage")
	public String getBlackListPage()
	{
		return "/blogs/visitor/black";
	}
	
	//友情链接页面跳转
	@GetMapping("/getSrc")
	public String getSrc()
	{
		return "/blogs/chart/eCharts";
	}
	
	//未处理消息页面跳转
	@GetMapping("/getUnprocessedMessagePage")
	public String getUnprocessedMessagePage()
	{
		return "/blogs/chart/eCharts";
	}
	
	//评论模块页面跳转
	@GetMapping("/getCommentPage")
	public String getCommentPage()
	{
		return "/blogs/chart/eCharts";
	}
	
	//本地图库页面跳转
	@GetMapping("/getPicturePage")
	public String getPicturePage()
	{
		return "/blogs/picture/picture";
	}
	
	//前台页面跳转
	@GetMapping("/getFrontPage")
	public String getFrontPage()
	{
		return "redirect://localhost:9040/index";
	}
	
	
	
	
	
	
	
	
	
}
