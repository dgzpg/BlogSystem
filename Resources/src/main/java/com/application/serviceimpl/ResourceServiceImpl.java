package com.application.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.application.Entity.Resource;
import com.application.dao.ResourceDao;
import com.application.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService{

	@Autowired
	private ResourceDao resourceDao;
	
	//根据id查询资源
	public Resource getResoureceById(long id)
	{
		return resourceDao.getResoureceById(id);
	}
	
	//根据状态获取所有状态的资源数
	public List<Long> getCountByAllStatus()
	{
		List<Long> nums=new ArrayList<Long>();
		long num1=resourceDao.getCountByStatus(1);
		long num2=resourceDao.getCountByStatus(2);
		long num3=num1+num2;
		nums.add(num1);
		nums.add(num2);
		nums.add(num3);
		return nums;
	}
	
	
	//根据状态查询所有资源，返回资源的page
	public Page<Resource> getSourcePageByStatus(int status,Pageable pageable)
	{
		return resourceDao.getSourcePageByStatus(status, pageable);
	}
	
	
	//根据状态获取具体某页的博客
	public Page<Resource> getSourceSpecificPageByStatus(int status,Pageable pageable)
	{
		return resourceDao.getSourceSpecificPageByStatus(status, pageable);
	}
	
	//根据资源状态和名字查找资源
	public Page<Resource> getResourceByStatusAndContent(int status,String content,Pageable pageable)
	{
		return resourceDao.getResourceByStatusAndContent(status, content, pageable);
	}
	
	
	//根据资源名进行模糊匹配查询资源
	public Page<Resource> getResourceByName(String name,Pageable pageable)
	{
		return resourceDao.getResourceByName(name, pageable);
	}
	
	//获取所有资源，返回资源的page
	public Page<Resource> getAllResourcePage(Pageable pageable)
	{
		return resourceDao.getAllResourcePage(pageable);
	}
		
	
	
	//修改资源
	public boolean updateResource(Resource resource)
	{
		return resourceDao.updateResource(resource);
	}
			
	//添加资源
	public boolean addResource(Resource resource)
	{
		return resourceDao.addResource(resource);
	}
	
	//删除资源
	public boolean deleteResource(long id)
	{
		return resourceDao.deleteResource(id);
	}
}
