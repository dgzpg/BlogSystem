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

import com.application.Entity.Blog;
import com.application.Entity.BlogType;
import com.application.annotation.MyLog;
import com.application.repository.BlogRepository;
import com.application.service.BlogService;
import com.application.service.BlogTypeService;
import com.fasterxml.jackson.core.sym.Name;
/*import com.github.andrewoma.dexx.collection.Set;*/

@Controller
public class BlogTypeManager {

	@Autowired
	private BlogTypeService blogTypeService;
	
	@Autowired
	private BlogService blogService;
	
	
	//获取所有博客种类并进行分页

	@GetMapping("/selectBlogTypeListByPage")
	@ResponseBody
	public Page<BlogType> selectBlogTypeListByPage(HttpServletRequest request)
	{
		
		int page=Integer.parseInt(request.getParameter("page"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		System.out.println("进入博客种类控制端，page为"+page+" pageSize为"+pageSize);
		String typeName=request.getParameter("typename");
		Sort sort=new Sort(Direction.DESC,"type_id");
		Pageable pageable=new PageRequest(page-1, pageSize,sort);
		Page<BlogType> blogTypes=blogTypeService.getAllBlogTypes(typeName,pageable);
		System.out.println(page+""+pageSize);
		return blogTypes;
	}
	
	
	//根据类别来查询博客类别，传入的参数是类别的名称，字符串类型
	//返回的是一个类别数组
	@GetMapping("/selectBlogType")	
	@ResponseBody
	public List<BlogType> selectBlogType(String type)
	{
		return blogTypeService.getAllBlogType();
	}
	
	//根据博客类别id查询博客类别
	@GetMapping("/selectBlogTypeById")
	@ResponseBody
	public BlogType selectBlogTypeById(HttpServletRequest request)
	{
		long id=Long.parseLong(request.getParameter("id"));
		System.out.println("typeId为:"+id);
		return blogTypeService.getBlogTypeById(id);
	}
	
	//根据博客类别id更新博客类别
	@MyLog(value="修改博客类别")
	@PostMapping("/updateBlogType")
	@ResponseBody
	public int updateBlogType(HttpServletRequest request)
	{
		long typeId=Long.parseLong(request.getParameter("id"));
		String type=request.getParameter("typename");
		System.out.println("typeId:"+typeId+"type:"+type);
		List<BlogType> aBlogTypes=blogTypeService.getBlogTypeByName(type);
		System.out.println(aBlogTypes);
		if(blogTypeService.getBlogTypeByName(type).size()>0)
		{
			return 2;
		}
		BlogType blogType=new BlogType();
		blogType.setTypeId(typeId);
		blogType.setType(type);
		int num=blogService.updateBlogTypeName(typeId, type);
		System.out.println("修改博客类别影响的行数为："+num);
		if(blogTypeService.editType(blogType))
		{
			
			return 1;
		}
		else
			return 0;
	}
	
	
	
	//获取最新的五个博客类型，并且获取相应的前6篇文章
	
	@GetMapping("/selectBlogByAllType")
	@ResponseBody
	public List<BlogType> selectBlogByAllType()
	{
		Sort sort=new Sort(Direction.DESC,"type_id");
		Pageable pageable=new PageRequest(0, 5,sort);
		List<BlogType> blogTypes=blogTypeService.getAllBlogTypeForPage(pageable).getContent();
		System.out.println("第一个元素的id 为："+blogTypes.get(0).getTypeId());
		List<Blog> blogs=new ArrayList();
		for(int i=0;i<blogTypes.size();i++)
		{
			blogs=blogService.getBlogsByTypeNameForPage(blogTypes.get(i).getType(), new PageRequest(0, 6, sort)).getContent();
			blogTypes.get(i).setBlogs(blogs);
		}
		return blogTypes;
	}
	
	//获取所有的博客种类
	@GetMapping("/selectAllBlogTypeForList")
	@ResponseBody
	public List<BlogType> selectAllBlogTypeForList(HttpServletRequest request)
	{
		
		Sort sort=new Sort(Direction.DESC,"type_id");
		Pageable pageable=new PageRequest(0, 10,sort);
		List<BlogType> blogTypes=blogTypeService.getAllBlogTypeForPage(pageable).getContent();
		return blogTypes;
	}
	
	//获取最新一个类别的id
	@GetMapping("/getLatestDataTypeId")
	@ResponseBody
	public long getLatestDataTypeId()
	{
		Sort sort=new Sort(Direction.DESC,"type_id");
		Pageable pageable=new PageRequest(0, 10,sort);
		List<BlogType> blogTypes=blogTypeService.getAllBlogTypeForPage(pageable).getContent();
		return blogTypes.get(0).getTypeId();
	}
	
	//添加博客类别
	@MyLog(value="添加博客类别")
	@PostMapping("/addBlogType")
	@ResponseBody
	public int addBlogType(HttpServletRequest request)
	{
		String typeName=request.getParameter("typename");
		if(blogTypeService.getBlogTypeByName(typeName).size()>0)
		{
			return 2;
		}
		BlogType blogType=new BlogType();
		blogType.setType(typeName);
		blogType.setBlogs(null);
		blogType.setTypeNum(0);
		String time=null;
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		time=simpleDateFormat.format(new Date());
		blogType.setCreateTime(time);
		if(blogTypeService.addType(blogType))
		{
			return 1;
		}
		else
			return 0;
	}
	
	@MyLog(value="删除博客类别")
	@GetMapping("/deleteBlogType")
	@ResponseBody
	public int deleteBlogType(HttpServletRequest request)
	{
		long id=Long.parseLong(request.getParameter("id"));
		if(blogTypeService.deleteBlogType(id))
			return 1;
		else
			return 0;
	}
}
