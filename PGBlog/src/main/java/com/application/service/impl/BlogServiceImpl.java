package com.application.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.application.Entity.Blog;
import com.application.Entity.BlogType;

import com.application.Entity.PageInfo;
import com.application.Entity.PublishedDate;
import com.application.dao.BlogDao;

import com.application.service.BlogService;
import com.application.service.BlogTypeService;

@Service
public class BlogServiceImpl implements BlogService{
	
		@Autowired
		private BlogDao blogDao;
		@Autowired
		private BlogTypeService blogTypeService;
		
		
		

		

		//查询所有的博客，按照时间的降序排序
		//已修改
		public Page<Blog> getAllBlogs(Pageable pageable) {
			
			
			return blogDao.getAllBlogs(pageable);
		}

		//根据博客的三种状态获取博客的具体某页的数据
		//已修改
		public Page<Blog> getBlogByStatusForPart(int status,Pageable pageable)
		{
			return blogDao.getBlogByStatusForPart(status, pageable);
		}
				
		//根据博客置顶来获取博客的具体某页的数据
		//已修改
		public Page<Blog> getBlogByTopForPart(int top, Pageable pageable)
		{
			return blogDao.getBlogByTopForPart(top, pageable);
		}
				
		//根据博客推荐来获取博客的具体某页的数据
		//已修改
		public Page<Blog> getBlogByRecommendForPage(int recommend,Pageable pageable)
		{
			return blogDao.getBlogByRecommendForPage(recommend, pageable);
		}
		
		

		
		//根据博客状态进行查询博客
		//已修改
		public Page<Blog> getBlogsByStatus(int status,Pageable pageable) {
			// TODO Auto-generated method stub
			return blogDao.getBlogsByStatus(status,pageable);
		}
		
		//查询所有的博客，返回的是一个long类型的数组，表示三种状态的博客数量
		//已修改
		public List<Integer> getAllBlogStatus()
		{
			List<Integer> num=new ArrayList<Integer>();
			num.add( blogDao.getBlogListByStatus(2).size());
			num.add(blogDao.getBlogListByStatus(1).size());
			num.add(blogDao.getBlogListByStatus(-1).size());
			return num;
		}
		
		//根据博客是否推荐来进行查询博客
		//已修改
		public Page<Blog> getBlogsByRecommend(int recommend,Pageable pageable) {
			
			
			return blogDao.getBlogsByRecommend(recommend, pageable);
		}
		
		//修改博客类型的时候修改博客的类别名
		//已修改
		public int updateBlogTypeName(long typeId,String typeName)
		{
			return blogDao.updateBlogTypeName(typeId, typeName);
		}
		
		
		//根据博客的类别明获取具体某页的数据
		//已修改
		public Page<Blog> getBlogsByTypeNameForPage(String typeName,Pageable pageable)
		{
			return blogDao.getBlogsByTypeNameForPage(typeName, pageable);
		}
		
		//查询所有博客类别
		public Set<BlogType> getAllBlogType()
		{
			return null;
		};
		
		//根据博客的类别名进行查询
		public Set<Blog> getBlogsByTypeName(String typeName) {
			// TODO Auto-generated method stub
			return null;
		}
		
		

		//根据博客是否置顶来查询博客
		//已修改
		public Page<Blog> getBlogsByTop(int top,Pageable pageable) {
			
			return blogDao.getBlogsByTop(top, pageable);
		}

		//根据博客的关键字来查询博客
		public Page<Blog> getBlogsByKeyWord(String keyWord,Pageable pageable) {
			// TODO Auto-generated method stub
			return null;
		}

		//根据博客id查询博客，将blog进行封装，将blog中的关键字变成一个集合
		//已修改
		public Blog getBlogById(long id) {
			Blog blog=blogDao.getBlogById(id);
			
			return blog;
		}

		//根据类别id查询博客
		//已修改
		public Page<Blog> getBlogsByTypeId(long typeId,Pageable pageable) {
			
			return blogDao.getBlogsByTypeId(typeId, pageable);
		}
		
		//根据博客类别的id获取具体某页的内容
		//已修改
		public Page<Blog> getBlogByTypeIdForOnePage(long typeId,Pageable pageable)
		{
			return blogDao.getBlogByTypeIdForOnePage(typeId, pageable);
		}

		//根据发表时间获取今天，昨天，前天的博客数量，返
		public List<Integer> getBlogsByDay() {
			
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
			String today=simpleDateFormat.format(date);
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(calendar.DATE, date.getDate()+1);
			String tomorrow=simpleDateFormat.format(calendar.getTime());
			
			calendar.set(calendar.DATE, date.getDate()-1);
			String yesterday=simpleDateFormat.format(calendar.getTime());
			
			calendar.set(calendar.DATE, date.getDate()-2);
			String day3=simpleDateFormat.format(calendar.getTime());
			
			System.out.println("明天为："+tomorrow+" 今天为："+today+" 昨天为："+yesterday+" 前天为："+day3);
			
			List<Integer> nums=new ArrayList<Integer>();
			nums.add( blogDao.getBlogsByDate(today, tomorrow).size());
			nums.add( blogDao.getBlogsByDate(yesterday, today).size());
			nums.add( blogDao.getBlogsByDate(day3, yesterday).size());
			
			return nums;
		}

		//根据发表时间来查询博客，返回博客数量
		public long getBlogCountByDate(int status,String startTime,String endTime)
		{
			return blogDao.getBlogCountByDate(status,startTime, endTime);
		}

		//根据博客id彻底删除博客
		//已修改
		public boolean deleteBlog(long id) {
			// TODO Auto-generated method stub
			return blogDao.deleteBlog(id);
		}

		//修改博客

		//已修改
		public boolean editBlog(Blog blog) {
			
			return blogDao.editBlog(blog);
		}

		//添加博客
		//已修改
		public boolean addBlog(Blog blog,long typeId) {
			//当前type保存的是类别的id，需要转换为实际的long类型
			
			BlogType blogType=blogTypeService.getBlogTypeById(typeId);
			String type=blogType.getType();
			blog.setType(type);
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			blog.setPublishedTime(simpleDateFormat.format(new Date()));
			blogType.getBlogs().add(blog);
			blogType.setTypeNum(blogType.getBlogs().size());
			try {
				blogDao.addBlog(blog);
				blogTypeService.editType(blogType);
				return true;
			} catch (Exception e) {
				return false;
			}
			
			
			
		}
		//根据浏览量进行倒序排序
		public List<Blog> getBlogsByPageView() {
			// TODO Auto-generated method stub
			return blogDao.getBlogsByPageView();
		}
		/*//根据博客的点击量进行倒序排序
		public Set<Blog> getBlogsByCommentNum() {
			// TODO Auto-generated method stub
			return null;
		}*/

		//根据博客id查询博客类别id
		//已修改
		public long getBlogTypeIdById(long id)
		{
			return blogDao.getBlogTypeIdById(id);
		}
		
		
}
