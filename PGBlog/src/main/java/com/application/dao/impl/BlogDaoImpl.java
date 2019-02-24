package com.application.dao.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.application.Entity.Blog;
import com.application.Entity.BlogType;
import com.application.dao.BlogDao;
import com.application.repository.BlogRepository;

@Service
public class BlogDaoImpl implements BlogDao {

	
	@Autowired
	private BlogRepository blogRepository;
	
	
	
	//查询所有的博客，按照时间的降序排序
	//已修改
	public Page<Blog> getAllBlogs(Pageable pageable) {
	
		return blogRepository.findAll(pageable);
	}

	//根据博客状态进行查询博客，返回的是该状态的所有数据
	//已修改
	public Page<Blog> getBlogsByStatus(int  status,Pageable pageable) {
		
		return blogRepository.getBlogsByStatus(status,pageable);
	}
	
	//根据博客的三种状态获取博客的具体某页的数据
	//已修改
	public Page<Blog> getBlogByStatusForPart(int status,Pageable pageable)
	{
		return blogRepository.getBlogByStatusForPart(status, pageable);
	}
			
	//根据博客置顶来获取博客的具体某页的数据
	//已修改
	public Page<Blog> getBlogByTopForPart(int top, Pageable pageable)
	{
		return blogRepository.getBlogByTopForPart(top, pageable);
	}
	
	//修改博客类型的时候修改博客的类别名
	//已修改
	public int updateBlogTypeName(long typeId,String typeName)
	{
		return blogRepository.updateBlogTypeName(typeId, typeName);
	}
			
	
	//根据博客的类别明获取具体某页的数据
	//已修改
	public Page<Blog> getBlogsByTypeNameForPage(String typeName,Pageable pageable)
	{
		return blogRepository.getBlogsByTypeNameForPage(typeName, pageable);
	}
			
	//根据博客推荐来获取博客的具体某页的数据
	//已修改
	public Page<Blog> getBlogByRecommendForPage(int recommend,Pageable pageable)
	{
		return blogRepository.getBlogByRecommendForPart(recommend, pageable);
	}
	
	//根据博客状态查询博客，返回一个list数组
	//已修改
	public List<Blog> getBlogListByStatus(int status)
	{
		return blogRepository.getBlogListByStatus(status);
	}
	
	//根据博客的类别名进行查询
	public Set<Blog> getBlogsByTypeName(String typeName) {
		
		return blogRepository.getBlogsByTypeName(typeName);
	}
	
	//根据博客是否推荐来进行查询博客
	//已修改
	public Page<Blog> getBlogsByRecommend(int recommend,Pageable pageable) {
			
		return blogRepository.getBlogsByRecommend(recommend,pageable);
	}
	
	//查询所有博客类别
	public Set<BlogType> getAllBlogType()
	{
		return null;
	};
	
	

	//根据博客是否置顶来查询博客
	//已修改
	public Page<Blog> getBlogsByTop(int top,Pageable pageable) {
		
		return blogRepository.getBlogsByTop(top,pageable);
	}

	//根据博客的关键字进行模糊匹配来查询博客
	public Page<Blog> getBlogsByKeyWord(String keyWord,Pageable pageable) 
	{
		return blogRepository.getBlogsByStatusForPage(1, keyWord, pageable);
	}


	//根据博客id查询博客
	public Blog getBlogById(long id) {
		
		return blogRepository.findById(id).get();
	}

	//根据类别id查询博客
	//已修改
	public Page<Blog> getBlogsByTypeId(long typeId,Pageable pageable) {
		
		return blogRepository.getBlogsByTypeId(typeId,pageable);
	}
	
	//根据博客类别的id获取具体某页的内容
	//已修改
	public Page<Blog> getBlogByTypeIdForOnePage(long typeId,Pageable pageable)
	{
		return blogRepository.getBlogByTypeIdForOnePage(typeId, pageable);
	}

	

	//根据发表的时间段来查询博客
	public List<Blog> getBlogsByDate(String firstDate, String lastDate) {
		
		return blogRepository.getBlogsByDate(firstDate, lastDate);
	}

	//根据发表时间来查询博客，返回博客数量
	public long getBlogCountByDate(int status,String startTime,String endTime)
	{
		return blogRepository.getBlogCountByDate(status,startTime, endTime);
	}
	

	//根据博客id彻底删除博客
	public boolean deleteBlog(long id) {
		try {
			blogRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return  false;
		}
		
	}

	//修改博客，针对不同的情况修改不同的内容
	
	//已修改
	public boolean editBlog(Blog blog) {
		
		try {
			Blog blog2=blogRepository.findById(blog.getBlogid()).get();
			blog2=blog;
			blogRepository.saveAndFlush(blog2);
			
			
			return true;
			
		} catch (Exception e) {
			return false;
		}
		
	}

	//添加博客
	public boolean addBlog(Blog blog) {
		try {
			blogRepository.saveAndFlush(blog);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	//根据浏览量进行倒序排序
	public List<Blog> getBlogsByPageView() {
		
		return blogRepository.getBlogsByPageView();
	}
	
	//根据博客的点击量进行倒序排序
	/*public Set<Blog> getBlogsByCommentNum() {
		
		return blogRepository.getBlogsByCommentNum();
	}*/
	
	//根据博客id查询博客类别id
	//已修改
	public long getBlogTypeIdById(long id)
	{
		return blogRepository.getBlogTypeIdById(id);
	}

}
