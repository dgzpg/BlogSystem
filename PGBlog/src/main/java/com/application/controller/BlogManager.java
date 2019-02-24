package com.application.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.Entity.Blog;
import com.application.Entity.BlogType;
import com.application.annotation.MyLog;
import com.application.service.BlogService;
import com.application.service.BlogTypeService;
import com.application.service.PictureService;



@RestController
public class BlogManager {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private BlogTypeService blogTypeService;
	
	@Autowired
	private PictureService pictureService;

		//添加博客
	
		@MyLog(value="添加博客")
		@PostMapping("/addBlog")
		@ResponseBody
		public int addBlog(HttpServletRequest request)
		{
			//System.out.println(request.getParameter("content"));
			HttpSession session=request.getSession();
		//	String userName=session.getAttribute("userName");
			String title=request.getParameter("title");
			String introduction=request.getParameter("introduction");
			//System.out.println("类别id为："+request.getParameter("type.id"));
			long typeId=Long.parseLong(request.getParameter("type.id"));
			String keyWord=request.getParameter("keyword");
			String content=request.getParameter("content");
			
			int status=Integer.parseInt(request.getParameter("status"));
			long pictureId=Long.parseLong(request.getParameter("images"));
			System.out.println("图片id为："+pictureId);
			System.out.println("content 的大小为："+content.length());
			
			String picture="http://localhost:9010"+pictureService.getPictureById(pictureId).getPath();
			
		
		
			Blog blog=new Blog();
			blog.setTitle(title);
			blog.setIntroduction(introduction);
			blog.setType("javaSE");
			blog.setKeyWord(keyWord);
			blog.setContent(content);
			blog.setPicturePath(picture);
			blog.setStatus(status);
			
			
			
			
			if(blogService.addBlog(blog,typeId)==true) 
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
	
	
	
	
	//修改博客访问量
	@GetMapping("editBlogPageView")
	@ResponseBody
	public void editBlogPageView(HttpServletRequest request)
	{
		long id=Long.parseLong(request.getParameter("id"));
		long pageView=Long.parseLong(request.getParameter("pageView"));
		Blog blog=blogService.getBlogById(id);
		blog.setPageView(pageView);
		blogService.editBlog(blog);
		
	}
		
	//修改博客，包括博客的状态，置顶和推荐
	@PostMapping("/editBlog")
	@ResponseBody
	public int editBlog(HttpServletRequest request)
	{
		System.out.println("id为:"+request.getParameter("id"));
		long blogId=Long.parseLong(request.getParameter("id"));
		Blog blog=blogService.getBlogById(blogId);
	
		//修改博客的状态
		if(request.getParameter("status")!=null)
		{
			int status=Integer.parseInt(request.getParameter("status"));
			blog.setStatus(status);
		}
		
		//修改是否推荐
		if(request.getParameter("isrecommend")!=null)
		{
			System.out.println("修改博客是否推荐"+request.getParameter("isrecommend"));
			int recommend=Integer.parseInt(request.getParameter("isrecommend"));
			blog.setRecommend(recommend);
		}
		//修改置顶
		if(request.getParameter("istop")!=null)
		{
			int top=Integer.parseInt(request.getParameter("istop"));
			blog.setTop(top);
		}
		if(blogService.editBlog(blog))
		{
			return 1;
		}
		else
		{
			return 0;
		}
		
		
	}
	
	
	//进入修改页面
	@GetMapping("/edit")
	public String edit(@PathParam("id")String id,Model model)
	{
		long blogId=Integer.parseInt(id);
		System.out.println("id"+blogId);
		model.addAttribute("blogId", blogId);
		return "blogs/blog/updateBlog";
	}
	
	
	//进入博客管理页面
	@GetMapping("/toBlogTable")
	public String toBlogTable()
	{
		return "blogs/blog/blogTable";
	}
	
	//更新博客，博客的所有信息均可更改
	@MyLog(value="修改博客")
	@PostMapping("/updateBlog")
	@ResponseBody
	public int updateBlog(HttpServletRequest request)
	{
		
		long id=Long.parseLong(request.getParameter("id"));
		Blog blog=blogService.getBlogById(id);
		String title=request.getParameter("title");
		String introduction=request.getParameter("introduction");
		long typeId=Long.parseLong(request.getParameter("type.id"));
		String type=blogTypeService.getBlogTypeById(typeId).getType();
		
		String keyWord=request.getParameter("keyword");
		String content=request.getParameter("content");
		int status=Integer.parseInt(request.getParameter("status"));
		System.out.println("图片id为："+request.getParameter("images"));
		long pictureId=Long.parseLong(request.getParameter("images"));
		
		String picture=null;
		if(pictureId>0)
		{
			picture=pictureService.getPictureById(pictureId).getPath();
		}
		else 
		{
			picture="0";
		}
		System.out.println("picture为："+picture);
		blog.setTitle(title);
		blog.setIntroduction(introduction);
		blog.setType(type);
		
		blog.setKeyWord(keyWord);
		blog.setContent(content);
		blog.setStatus(status);
		blog.setPicturePath(picture);
		
		if(blogService.editBlog(blog))
			return 1;
		else
			return 0;
	}
	
	//删除博客
	@PostMapping("/deleteBlog")
	@ResponseBody
	public int deleteBlog(HttpServletRequest request)
	{
		long id=Long.parseLong(request.getParameter("id"));
		String type=blogService.getBlogById(id).getType();
		if(blogService.deleteBlog(id)){
			BlogType blogType=blogTypeService.getBlogTypeByName(type).get(0);
			List<Blog> blogs= blogType.getBlogs();
			blogs.remove(blogService.getBlogById(id));
			blogType.setBlogs(blogs);
			blogType.setTypeNum(blogs.size());
			blogTypeService.editType(blogType);
			return 1;
		}
		return 0;
	}
}
