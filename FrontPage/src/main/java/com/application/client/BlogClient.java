package com.application.client;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.application.Entity.Blog;
import com.application.entity.PageEntity;

//获取相关服务的服务名
@FeignClient("BlogService")
public interface BlogClient {

	
	//获取所有类别
	@RequestMapping(value="selectBlogByAllType",method=RequestMethod.GET)
	public String	selectBlogByAllType();
	
	//获取推荐的、最新的、置顶的指定页的数据
	@RequestMapping(value="getBlogListBySpecifitPage",method=RequestMethod.GET)
	public String getBlogListBySpecifitPage(@RequestParam("pageSize")String pageSize,@RequestParam("page")String page,@RequestParam("status")String status);
	

	//根据博客id获取博客
	@RequestMapping(value="selectBlogById",method=RequestMethod.GET)
	public String selectBlogById(@RequestParam("id")String id);
	
	@RequestMapping(value="getBlogListBySpecifitPage",method=RequestMethod.GET)
	public String selectBlogByTypeIdForPage(@RequestParam("pageSize")String pageSize,@RequestParam("page")String page,@RequestParam("status")String status,@RequestParam("typeId")String typeId);
	
	@RequestMapping(value="getBlogListBySpecifitPage",method=RequestMethod.GET)
	public String selectBlogByPageView(@RequestParam("pageSize")String pageSize,@RequestParam("page")String page,@RequestParam("status")String status);
	
	//修改博客的访问量
	@RequestMapping(value="editBlogPageView",method=RequestMethod.GET)
	public String editBlogPageView(@RequestParam("id")String id, @RequestParam("pageView")long pageView);
	
	@RequestMapping(value="selectAllBlogTypeForList",method=RequestMethod.GET)
	public String selectBlogTypeForList();
	
	@RequestMapping(value="selectBlogListByPage",method=RequestMethod.GET)
	public String selectBlogListByPage(@RequestParam("pageSize")String pageSize,@RequestParam("page")String page,@RequestParam("keyWord")String keyWord);
	
	//获取最新的一个类别的id
	@RequestMapping(value="getLatestDataTypeId",method=RequestMethod.GET)
	public String getLatestDataTypeId();
	
	//根据关键字搜索博客
	@RequestMapping(value="getBlogListBySpecifitPage",method=RequestMethod.GET)
	public String getBlogListByPageForKeyWord(@RequestParam("pageSize")String pageSize,@RequestParam("page")String page,@RequestParam("keyWord")String keyWord,@RequestParam("status")int status);
	
	//根据点击排行获取博客
	//public Page<Blog> selectBlogByPageViewForPage();
}
