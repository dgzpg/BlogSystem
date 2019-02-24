package com.application.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.application.Entity.BlogType;

public interface BlogTypeService {
	
		//添加博客类别
		public boolean addType(BlogType blogType);
		
		//修改类别
		public boolean editType(BlogType blogType);
		
		//根据类别名查询类别
		public List<BlogType> getBlogTypeByName(String name);
		
		//获取指定页的所有博客类别
		public Page<BlogType> getAllBlogTypeForPage(Pageable pageable);
		
		//根据类别id查询类别
		public BlogType getBlogTypeById(long id);
		
		//根据类别id删除类别
		public boolean deleteBlogType(long id);
		
		//查询所有博客类别并进行分页，
		//已修改
		public Page<BlogType> getAllBlogTypes(String typeName,Pageable pageable);

		//查询所有博客类型，返回一个list 集合
		//已修改
		public List<BlogType> getAllBlogType();
}
