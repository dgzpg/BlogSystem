package com.application.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.application.Entity.BlogType;

public interface BlogTypeRepository extends JpaRepository<BlogType, Long>{

	@Query(value="select * from blog_type order by type_id desc",nativeQuery=true)
	public List<BlogType> getAllBlogType();
	
	
	//根据博客类别名查询
	@Query(value="select * from blog_type where type=?1",nativeQuery=true)
	public List<BlogType> getBlogTypeByName(String type);
	
	//根据具体的某页获取所有的博客类别
	@Query(value="select * from blog_type",countQuery="select count(*) from blog_type",nativeQuery=true)
	public Page<BlogType> getAllBlogTypeForPage(Pageable pageable);
	
	@Query(value="select * from blog_type where type like CONCAT('%',?1,'%') order by ?#{#pageable}",countQuery="select count(*) from blog_type",nativeQuery=true)
	public Page<BlogType> getAllBlogTypes(String typeName,Pageable pageable);
}
