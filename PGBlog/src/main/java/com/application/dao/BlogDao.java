package com.application.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.application.Entity.Blog;
import com.application.Entity.BlogType;

public interface BlogDao  {

		//查询所有的博客，按照时间的降序排序
		//已修改
		public Page<Blog> getAllBlogs(Pageable pageable);
		
		
		
		//根据博客状态进行查询博客
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
		
		//根据博客状态查询博客，返回一个list数组
		//已修改
		public List<Blog> getBlogListByStatus(int status);
		
		//根据博客的类别名进行查询
		public Set<Blog> getBlogsByTypeName(String typeName);
		
		//根据博客的类别明获取具体某页的数据
		//已修改
		public Page<Blog> getBlogsByTypeNameForPage(String typeName,Pageable pageable);
		
		//根据博客是否推荐来进行查询博客
		//已修改
		public Page<Blog> getBlogsByRecommend(int recommend,Pageable pageable);
		
		//根据博客是否置顶来查询博客
		//已修改
		public Page<Blog> getBlogsByTop(int top,Pageable pageable);
		
		//根据博客的关键字进行模糊匹配来查询博客
		public Page<Blog> getBlogsByKeyWord(String keyWord,Pageable pageable) ;

		
		//根据博客id查询博客
		public Blog getBlogById(long id);
		
		//修改博客类型的时候修改博客的类别名
		//已修改
		public int updateBlogTypeName(long typeId,String typeName);
		
		
		//根据类别id查询博客
		//已修改
		public Page<Blog> getBlogsByTypeId(long typeId,Pageable pageable);
		
		//根据博客类别的id获取具体某页的内容
		//已修改
		public Page<Blog> getBlogByTypeIdForOnePage(long typeId,Pageable pageable);
		
		//查询所有博客类别
		public Set<BlogType> getAllBlogType();
		
		//根据发表的时间段来查询博客
		public List<Blog> getBlogsByDate(String firstDate,String lastDate);
		
		//根据发表时间来查询博客，返回博客数量
		public long getBlogCountByDate(int status,String startTime,String endTime);
		
		//根据博客id彻底删除博客
		public boolean deleteBlog(long id);
		
		//修改博客
		//已修改
		public boolean editBlog(Blog blog);
		
		//添加博客
		public boolean addBlog(Blog blog);
		
		//根据浏览量进行倒序排序
		public List<Blog> getBlogsByPageView();
		
		/*//根据博客的点击量进行倒序排序
		public Set<Blog> getBlogsByCommentNum();*/
		
		//根据博客id查询博客类别id
		//已修改
		public long getBlogTypeIdById(long id);
	
}
