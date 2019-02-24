package com.application.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.application.Entity.Blog;
import com.application.Entity.BlogType;

import com.application.Entity.PageInfo;
import com.application.Entity.PublishedDate;

public interface BlogService {

	
	
	
		//查询所有的博客，按照时间的降序排序
		//已修改
		public Page<Blog> getAllBlogs(Pageable pageable);
		
		
		
		//根据博客具体状态进行查询博客,返回的是所有数据的page
		//已修改
		public Page<Blog> getBlogsByStatus(int status,Pageable pageable);
		
		
		//根据博客的三种状态获取博客的具体某页的数据
		//已修改
		public Page<Blog> getBlogByStatusForPart(int status,Pageable pageable);
		
		//根据博客置顶来获取博客的具体某页的数据
		//已修改
		public Page<Blog> getBlogByTopForPart(int top, Pageable pageable);
		
		//根据博客推荐来获取博客的具体某页的数据
		//已修改
		public Page<Blog> getBlogByRecommendForPage(int recommend,Pageable pageable);
		
		
		//查询所有的博客，返回的是一个long类型的数组，表示三种状态的博客数量
		//已修改
		public List<Integer> getAllBlogStatus();
		
		
		//修改博客类型的时候修改博客的类别名
		//已修改
		public int updateBlogTypeName(long typeId,String typeName);
		
		//根据博客的类别明获取具体某页的数据
		//已修改
		public Page<Blog> getBlogsByTypeNameForPage(String typeName,Pageable pageable);
				
		
		//根据博客为推荐来进行查询博客
		//已修改
		public Page<Blog> getBlogsByRecommend(int recommend,Pageable pageable);
		
		//根据博客是否置顶来查询博客
		//已修改
		public Page<Blog> getBlogsByTop(int top,Pageable pageable);
		
		//根据博客的关键字进行模糊匹配来查询博客
		public Page<Blog> getBlogsByKeyWord(String keyWord,Pageable pageable) ;
				
		
		//根据博客id查询博客
		public Blog getBlogById(long id);
		
		
		
		//根据类别id查询博客
		//已修改
		public Page<Blog> getBlogsByTypeId(long typeId,Pageable pageable);
		
		//根据博客类别的id获取具体某页的内容
		//已修改
		public Page<Blog> getBlogByTypeIdForOnePage(long typeId,Pageable pageable);
		
		//根据博客的类别名进行查询
		public Set<Blog> getBlogsByTypeName(String typeName);
		
		//根据发表时间获取今天，昨天，前天的博客发表数量
		public List<Integer> getBlogsByDay();
		
		//根据发表时间来查询博客，返回博客数量
		public long getBlogCountByDate(int status,String startTime,String endTime);
		
		
		
		
		//根据博客id彻底删除博客
		public boolean deleteBlog(long id);
		
		//修改博客
		//已修改
		public boolean editBlog(Blog blog);
		
		//添加博客
		public boolean addBlog(Blog blog,long typeId);
		
		//根据浏览量进行倒序排序
		public List<Blog> getBlogsByPageView();
		
		/*//根据博客的点击量进行倒序排序
		public Set<Blog> getBlogsByCommentNum();*/
		
		//根据博客id查询博客类别id
		//已修改
		public long getBlogTypeIdById(long id);
}
