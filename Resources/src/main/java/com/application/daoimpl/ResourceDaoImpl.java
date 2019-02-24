package com.application.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.application.Entity.Resource;
import com.application.dao.ResourceDao;
import com.application.repository.ResourceRepository;

@Service
public class ResourceDaoImpl implements  ResourceDao{

	@Autowired
	private ResourceRepository resourceRepository;
	
	//根据id查询资源
	public Resource getResoureceById(long id)
	{
		return resourceRepository.findById(id).get();
	}
	
	//根据状态获取状态的资源数
	public long getCountByStatus(int status)
	{
		long num=resourceRepository.getCountByStatus(status).size();
		return num;
	}
	
	
	//根据状态查询所有资源，返回资源的page
	public Page<Resource> getSourcePageByStatus(int status,Pageable pageable)
	{
		return resourceRepository.getSourcePageByStatus(status, pageable);
	}
	
	//根据状态获取具体某页的博客
	public Page<Resource> getSourceSpecificPageByStatus(int status,Pageable pageable)
	{
		return resourceRepository.getSourceSpecificPageByStatus(status, pageable);
	}
			
		
	
	//根据资源状态和名字查找资源
	public Page<Resource> getResourceByStatusAndContent(int status,String content,Pageable pageable)
	{
		return resourceRepository.getResourceByStatusAndContent(status, content, pageable);
	}
	
	
	//根据资源名进行模糊匹配查询资源
	public Page<Resource> getResourceByName(String name,Pageable pageable)
	{
		return resourceRepository.getResourceByName(name, pageable);
	}
	
	
	//获取所有资源，返回资源的page
	public Page<Resource> getAllResourcePage(Pageable pageable)
	{
		return resourceRepository.getAllResourcePage(pageable);
	}
	
	//修改资源
	public boolean updateResource(Resource resource)
	{
		try {
			Resource resource2=resourceRepository.findById(resource.getId()).get();
			resource2=resource;
			resourceRepository.saveAndFlush(resource2);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
		
	//添加资源
	public boolean addResource(Resource resource)
	{
		try {
			resourceRepository.saveAndFlush(resource);
			return true;
		} catch (Exception e) {
			return false;
		}
	
	}
	
	//删除资源
	public boolean deleteResource(long id)
	{
		try {
			resourceRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			System.out.println("删除异常");
			return false;
		}
		
	}
}
