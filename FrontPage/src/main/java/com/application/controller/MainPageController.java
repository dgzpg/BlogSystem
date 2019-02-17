package com.application.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.application.Entity.Blog;
import com.application.Entity.BlogType;
import com.application.annotation.MyLog;
import com.application.client.BlogClient;
import com.application.client.ResourceClient;
import com.application.entity.Blogs;
import com.application.entity.PageEntity;

@Controller
public class MainPageController {

	@Autowired
	private BlogClient blogClient;
	
	@Autowired
	private ResourceClient resourceClient;
	@GetMapping("/index")
	public String index()
	{
		return "index";
	}
	
	@GetMapping("/test")
	public String test()
	{
		return "test";
	}
	
	//获取类别
	@GetMapping("/selectBlogByAllType")
	@ResponseBody
	public String selectBlogByAllType(HttpServletRequest request)
	{
		return blogClient.selectBlogByAllType();
	}
	
	//获取推荐、热门的、所有最新的文章
	@GetMapping("/getBlogListBySpecifitPage")
	@ResponseBody
	public String getBlogListBySpecifitPage(HttpServletRequest request)
	{
		System.out.println("进入前端博客控制中心");
		String pageSize=request.getParameter("pageSize");
		String page=request.getParameter("page");
		String status=request.getParameter("status");
		System.out.println("页面大小为:"+pageSize+" 页面为："+page+" 状态为："+status);
		return  blogClient.getBlogListBySpecifitPage(pageSize,page,status);
	}
	
	
	//获取点击排行的文章
	@GetMapping("/selectBlogByPageViewForPage")
	public String selectBlogByPageViewForPage(HttpServletRequest request)
	{
		return null;
	}
	

	//根据id获取博客
	@MyLog(value="查看博客")
	@GetMapping("/selectBlogById")
	public String selectBlogById(@PathParam("id")String id,Model model)
	{
		System.out.println("id为："+id);
		System.out.println(blogClient.selectBlogById(id));
		String result=blogClient.selectBlogById(id);
		JSONObject object= JSON.parseObject(result);
		Blogs blogs=JSONObject.toJavaObject(object, Blogs.class);
		System.out.println("博客类别id为："+blogs.getBlogTypeId()+"修改后");
		Blog blog=blogs.getBlog();
		blogClient.editBlogPageView(id,blog.getPageView()+1);
		long typeId=blogs.getBlogTypeId();
		model.addAttribute("blog", blog);
		model.addAttribute("typeId", typeId);
		return "info";
	}
	//获取下一篇文章
	@GetMapping("/selectPrevAndNextBlog")
	@ResponseBody
	public List<Blogs> selectPrevAndNextBlog(HttpServletRequest request)
	{
		long id=Long.parseLong(request.getParameter("id"));
		String id2=String.valueOf(id-1);
		String id3=String.valueOf(id+1);
		List<Blogs> blogs=new ArrayList<Blogs>();
		String result=null;
		JSONObject object=null;
		if(id==0)
		{
			blogs.add(null);
		}
		else
		{
			try {
				result=blogClient.selectBlogById(id2);
				object=JSON.parseObject(result);
				blogs.add(JSONObject.toJavaObject(object, Blogs.class));
				
			} catch (Exception e) {
				blogs.add(null);
			}
			
		
			
		}
		try {
			result=blogClient.selectBlogById(id3);
			object=JSONObject.parseObject(result);
			blogs.add(JSONObject.toJavaObject(object, Blogs.class));
		} catch (Exception e) {
			blogs.add(null);
		}
		
		return blogs;
	}
	
	
	//根据类别id获取指定类别
	@GetMapping("/selectBlogByTypeIdForPage")
	@ResponseBody
	public String selectBlogByTypeIdForPage(HttpServletRequest request)
	{
		String pageSize=request.getParameter("pageSize");
		String page=request.getParameter("page");
		String typeId=request.getParameter("typeId");
		
		//获取具体页的所有博客
		if(typeId.equals("all"))
		{
			
			return blogClient.getBlogListBySpecifitPage(pageSize, page, "1");
		}
		else {
			return blogClient.selectBlogByTypeIdForPage(pageSize, page, "4", typeId);
		}
			
			
		
		
		
	}
	
	//根据点击排行获取博客
	@GetMapping("/selectBlogByPageView")
	@ResponseBody
	public String selectBlogByPageView(HttpServletRequest request)
	{
		String pageSize=request.getParameter("pageSize");
		String page=request.getParameter("page");
		
		return blogClient.selectBlogByPageView(pageSize, page, "5");
	}
	
	
	//获取所有类别，返回一个list
	@GetMapping("/selectBlogTypeForList")
	@ResponseBody
	public String selectBlogTypeForList()
	{
		String result=blogClient.selectBlogTypeForList();
		/*JSONObject object=JSON.parseObject(result);
		Page<BlogType> pages=JSONObject.toJavaObject(object, Page.class);
		System.out.println(pages);
		List<BlogType> blogTypes=pages.getContent();*/
		
		return result;
	}
	
	
	//进入技术博客页面
	@GetMapping("/getList")
	public String getList(Model model)
	{
		int key=1;
		String keyword="all";
		model.addAttribute("key", key);
		model.addAttribute("keyword", keyword);
		return "list";
	}
	
	//进入result页面
	@GetMapping("/toResult")
	public String toResult(@PathParam("typeId")String typeId,Model model)
	{
		
		long id=Long.parseLong(typeId);
		if(id==0)
		{
			
			typeId=blogClient.getLatestDataTypeId();
		}
		int type=1;
		model.addAttribute("typeId", typeId);
		model.addAttribute("type", type);
		return "result";
	}
	
	//关键字搜索
	@PostMapping("/toSearch")
	public String toSearch(HttpServletRequest request,Model model)
	{
		System.out.println("关键字为："+request.getParameter("keyboard"));
		int type=2;
		String typeId=request.getParameter("keyboard");
		model.addAttribute("typeId", typeId);
		model.addAttribute("type", type);
		return "result";
	}
	
	//关键字搜索博客
	@MyLog(value="关键字搜索博客")
	@GetMapping("/getBlogListByPageForKeyWord")
	@ResponseBody
	public String getBlogKeyWord(HttpServletRequest request)
	{
		String pageSize=request.getParameter("pageSize");
		String page=request.getParameter("page");
		String keyWord=request.getParameter("keyWord");
		System.out.println("关键字的pageSize: "+pageSize+" page: "+page+" keyWord: "+keyWord);
		
		return blogClient.getBlogListByPageForKeyWord(pageSize, page, keyWord,6);
	}
	
	
	//进入资源页面
	@GetMapping("/toResource")
	public String toResource()
	{
		return "resource";
	}
	
	//根据资源名模糊匹配以及资源状态获取指定页的资源
	@MyLog(value="关键字查询资源")
	@GetMapping("/getSourceSpecificPageByStatus")
	@ResponseBody
	public String getSourceSpecificPageByStatus(HttpServletRequest request)
	{
		String pageSize=request.getParameter("pageSize");
		String page=request.getParameter("page");
		String content=request.getParameter("keyword");
		System.out.println("content为 :"+content);
		return resourceClient.getAllResources(pageSize, page,content);
	}
	
	
	
	//进入登录页面
	@GetMapping("/login")
	public String login()
	{
		return "redirect://localhost:9010/login";
	}
	

}
