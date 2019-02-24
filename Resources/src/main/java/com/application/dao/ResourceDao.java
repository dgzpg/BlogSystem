package com.application.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.application.Entity.Resource;

public interface ResourceDao {

	//根据id查询资源
	public Resource getResoureceById(long id);
	
	//根据状态获取状态的资源数
	public long getCountByStatus(int status);
	
	//根据状态查询所有资源，返回资源的page
	public Page<Resource> getSourcePageByStatus(int status,Pageable pageable);
	
	//根据状态获取具体某页的博客
	public Page<Resource> getSourceSpecificPageByStatus(int status,Pageable pageable);
		
	
	//根据资源状态和名字查找资源
	public Page<Resource> getResourceByStatusAndContent(int status,String name,Pageable pageable);
	
	
		
	
	//根据资源名进行模糊匹配查询资源
	public Page<Resource> getResourceByName(String name,Pageable pageable);
	
	//获取所有资源，返回资源的page
	public Page<Resource> getAllResourcePage(Pageable pageable);
	
	//修改资源
	public boolean updateResource(Resource resource);
	
	//添加资源
	public boolean addResource(Resource resource);
	
	//删除资源
	public boolean deleteResource(long id);
	
	
}
