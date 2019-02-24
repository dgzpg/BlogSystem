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

import com.application.Entity.Resource;
import com.application.annotation.MyLog;
import com.application.service.ResourceService;

@Controller
public class ResourceManager {

	@Autowired
	private ResourceService resourceService;
	
	//获取不同状态的资源数
	@GetMapping("/selectResourceListByStatus")
	@ResponseBody
	public List<Long> selectResourceListByStatus()
	{
		
		return resourceService.getCountByAllStatus();
	}
	
	
	//根据资源状态获取资源，返回所有资源的page
	@GetMapping("/selectResourceListByPage")
	@ResponseBody
	public Page<Resource> selectResourceListByPage(HttpServletRequest request)
	{
		//获取页面大小
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		//获取当前展示第几页
		int pageNumber=Integer.parseInt(request.getParameter("page"));
		int status=Integer.parseInt(request.getParameter("status"));
		String content=request.getParameter("content");
		//System.out.println("name为："+name+",大小为："+name.length());
		//System.out.println("页面大小为："+pageSize+"，当前展示第"+pageNumber+"页，状态为："+status);
		Sort sort=new Sort(Direction.DESC,"resource_id");
		Pageable pageable =new PageRequest(pageNumber-1, pageSize,sort);
		//返回所有的资源
		
			if(content!=null)
			{
				if(status==0)
				{
					return resourceService.getResourceByName(content, pageable);
				}
				return resourceService.getResourceByStatusAndContent(status, content, pageable);
			}
			if(content==null)
			{
				if(status==0)
				{
					return resourceService.getAllResourcePage( pageable);
				}
				return resourceService.getSourcePageByStatus(status, pageable);
			}
	return null;
		
	
	}
	
	//根据资源内容和状态查找资源
	@GetMapping("/getSourceSpecificPageByStatus")
	@ResponseBody
	public Page<Resource> getSourceSpecificPageByStatus(HttpServletRequest request)
	{
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		int page=Integer.parseInt(request.getParameter("page"));
		Sort sort=new Sort(Direction.DESC,"resource_id");
		String content=request.getParameter("content");
		Pageable pageable=new PageRequest(page-1, pageSize, sort);
		//查询所有的
		if(content.equals("none"))
		{
			return resourceService.getSourceSpecificPageByStatus(1, pageable);
		}
		else
		{
			return resourceService.getResourceByStatusAndContent(1, content, pageable);
		}
	
		
	}
	
	
	//更新资源，一种是更新上架下架，一种是更新其所有内容
	//返回1表示更新成功，返回2表示更新失败，返回3表示更新错误
	@MyLog(value="更新资源")
	@PostMapping("/updateResource")
	@ResponseBody
	public int updateResource(HttpServletRequest request)
	{
		long id=Long.parseLong(request.getParameter("id"));
		int status=Integer.parseInt(request.getParameter("status"));
		int a=0;
		System.out.println("status为："+status+",id为："+id);
		try {
			Resource resource=resourceService.getResoureceById(id);
			//修改其内容
			if(status==3)
			{
				String name=request.getParameter("name");
				String content=request.getParameter("content");
				String src=request.getParameter("link");
				String password=request.getParameter("password");
				System.out.println(name+content+src+password);
				resource.setName(name);
				resource.setContent(content);
				resource.setSrc(src);
				resource.setPassword(password);
				if(resourceService.updateResource(resource))
					a=1;
				else
					a=2;
			}
			//修改其状态
			else
			{
				resource.setStatus(status);
				if(resourceService.updateResource(resource))
					a=1;
				else
					a=2;
				
			}
		} catch (Exception e) {
			a=0;
		}
		return a;
	}
	
	//删除资源
	//返回1表示删除成功，返回2表示删除失败，返回0表示操作失败
	@MyLog(value="删除资源")
	@GetMapping("/deleteResource")
	@ResponseBody
	public int deleteResource(HttpServletRequest request)
	{
		long id=Long.parseLong(request.getParameter("id"));
		System.out.println("删除的资源id为："+id);
		int a=0;
		try {
			if(resourceService.deleteResource(id))
				a=1;
			else
				a=2;
		} catch (Exception e) {
			a=0;
		}
		return a;
	}
	
	//根据资源id查询资源
	@GetMapping("/selectResourceById")
	@ResponseBody
	public Resource selectResourceById(HttpServletRequest request)
	{
		long id=Long.parseLong(request.getParameter("id"));
		System.out.println("资源的id为："+id);
		return resourceService.getResoureceById(id);
	}
	
	//添加资源
	@MyLog(value="添加资源")
	@PostMapping("/addResource")
	@ResponseBody
	public int addResource(HttpServletRequest request)
	{
		String name=request.getParameter("name");
		String content=request.getParameter("content");
		String src=request.getParameter("link");
		String password=request.getParameter("password");
		
		Resource resource=new Resource();
		resource.setName(name);
		resource.setContent(content);
		resource.setSrc(src);
		resource.setPassword(password);
		resource.setStatus(2);
		
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		resource.setPublishedTime(simpleDateFormat.format(new Date()));
		if(resourceService.addResource(resource))
			return 1;
		
		return 0;
	}
}
