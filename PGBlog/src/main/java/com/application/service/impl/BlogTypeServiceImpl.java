package com.application.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.application.Entity.BlogType;
import com.application.dao.BlogDao;
import com.application.dao.BlogTypeDao;
import com.application.service.BlogTypeService;

@Service
public class BlogTypeServiceImpl implements BlogTypeService{
	
	@Autowired
	private BlogTypeDao blogTypeDao;

		//添加博客类别
		//已修改
		public boolean addType(BlogType blogType) {
			// TODO Auto-generated method stub
			return blogTypeDao.addType(blogType);
		}

		//修改博客类别
		public boolean editType(BlogType blogType) {
			
			return blogTypeDao.editType(blogType);
		}

		//根据博客类别名查询博客类别
		public List<BlogType> getBlogTypeByName(String name) {
			// TODO Auto-generated method stub
			return blogTypeDao.getBlogTypeByName(name);
		}
		
		
		//获取指定页的所有博客类别
		public Page<BlogType> getAllBlogTypeForPage(Pageable pageable)
		{
			return blogTypeDao.getAllBlogTypeForPage(pageable);
		}
		
		
		//根据博客类别id查询博客类别
		public BlogType getBlogTypeById(long id)
		{
			
			return blogTypeDao.getBlogTypeById(id);
		};

		//删除博客类别
		public boolean deleteBlogType(long id) {
			// TODO Auto-generated method stub
			return blogTypeDao.deleteBlogType(id);
		}

		//获取所有博客类别并进行分页
		//已修改
		public Page<BlogType> getAllBlogTypes(String typeName,Pageable pageable) {
			// TODO Auto-generated method stub
			return blogTypeDao.getAllBlogTypes(typeName,pageable);
		}
	
		//获取所有类别，返回一个list集合
		//已修改
		public List<BlogType> getAllBlogType()
		{
			return blogTypeDao.getAllBlogType();
		}
}
