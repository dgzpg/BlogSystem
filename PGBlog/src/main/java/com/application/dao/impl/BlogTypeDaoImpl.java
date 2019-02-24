package com.application.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.application.Entity.BlogType;
import com.application.dao.BlogTypeDao;
import com.application.repository.BlogRepository;
import com.application.repository.BlogTypeRepository;

@Service
public class BlogTypeDaoImpl implements BlogTypeDao{

	@Autowired
	private BlogTypeRepository blogTypeRepository;
	
	
	//添加博客类别
	public boolean addType(BlogType blogType) {
		try {
			blogTypeRepository.saveAndFlush(blogType);
			return true;
		} catch (Exception e) {
			return false;
		}
		
		
	}

	//修改博客类别
	public boolean editType(BlogType blogType) {
		BlogType type=blogTypeRepository.findById(blogType.getTypeId()).get();
		type.setType(blogType.getType());
		try {
			blogTypeRepository.saveAndFlush(type);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	//根据博客类别名查询博客类别
	//已修改
	public List<BlogType> getBlogTypeByName(String name) {
		
		return blogTypeRepository.getBlogTypeByName(name);
	}
	
	
	//获取指定页的所有博客类别
	public Page<BlogType> getAllBlogTypeForPage(Pageable pageable)
	{
		return blogTypeRepository.getAllBlogTypeForPage(pageable);
	}
	
	//根据类别id查询类别
	public BlogType getBlogTypeById(long id)
	{
		return blogTypeRepository.findById(id).get();
	}

	//删除博客类别
	//已修改
	public boolean deleteBlogType(long id) {
		try {
			blogTypeRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
		 
	}

	//获取所有博客类别
	//已修改
	public Page<BlogType> getAllBlogTypes(String typeName,Pageable pageable) {
		
		return blogTypeRepository.getAllBlogTypes(typeName,pageable);
	}

	//查询所有的博客返回一个list
	//已修改
	public List<BlogType> getAllBlogType()
	{
		return blogTypeRepository.findAll();
	};
}
