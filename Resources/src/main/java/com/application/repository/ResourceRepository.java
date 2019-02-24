package com.application.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.application.Entity.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long>{
	
	//根据状态获取资源的数量
	@Query(countQuery="select * from resource where status=?1",nativeQuery=true)
	public List<Resource> getCountByStatus(int status);
	
	//根据状态获取资源，返回资源的page
	@Query(value="select * from resource where status=?1 order by ?#{#pageable}",countQuery="select count(*) from resource where status=?1",nativeQuery=true)
	public Page<Resource> getSourcePageByStatus(int status,Pageable pageable);
	
	//根据状态获取具体某页的博客
	@Query(value="select * from resource where status=?1",countQuery="select count(*) from resource where status=?1",nativeQuery=true)
	public Page<Resource> getSourceSpecificPageByStatus(int status,Pageable pageable);
	
	//获取所有资源返回资源的page
	@Query(value="select * from resource order by ?#{#pageable}",countQuery="select count(*) from resource",nativeQuery=true)
	public Page<Resource> getAllResourcePage(Pageable pageable);
	
	//根据资源状态和资源名称查找资源
	@Query(value="select * from resource  where status =?1 and content like CONCAT('%',?2,'%')",countQuery="select count(*) from resource where status=?1",nativeQuery=true)
	public Page<Resource> getResourceByStatusAndContent(int status,String content,Pageable pageable);
	
	//根据名字查询所有资源
	@Query(value="select * from resource  where name like CONCAT('%',?1,'%')",countQuery="select count(*) from resource ",nativeQuery=true)
	public Page<Resource> getResourceByName(String name,Pageable pageable);
	
	
	

}
