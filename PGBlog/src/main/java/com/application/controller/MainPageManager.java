package com.application.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.application.Entity.Blog;
import com.application.Entity.BlogType;
import com.application.Entity.Blogs;
import com.application.Entity.PageInfo;
import com.application.Entity.Picture;
import com.application.Entity.PublishedDate;
import com.application.Entity.Sources;
import com.application.annotation.MyLog;
import com.application.client.ResourceClient;
import com.application.dao.BlogDao;
import com.application.repository.BlogRepository;
import com.application.service.BlogService;
import com.application.service.BlogTypeService;
import com.application.service.PictureService;


@Controller
public class MainPageManager {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private BlogTypeService blogTypeService;
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private PictureService pictureService;
	
	@Autowired
	private ResourceClient resourceClient;

	@GetMapping("/index")
	public String index()
	{
		
		
		return "/blogs/index";
	}
	
	
	//获取资源的数目
	@GetMapping("/selectResourceListByStatus")
	@ResponseBody
	public String selectResourceListByStatus()
	{
		
		return resourceClient.getAllResourceCount();
	}
	
	//获取今日昨日博客发表量
	@GetMapping("/getBlogsByDay")
	@ResponseBody
	public List<Integer> getBlogsByDay()
	{
		return blogService.getBlogsByDay();
	}
	
	//今日访客，昨日访客和总访客
	@GetMapping("/selectVisitListByDate")
	@ResponseBody
	public List<Long> selectVisitListByDate()
	{
		long now=0;
		long yes=0;
		long all=0;
		Calendar calendar=Calendar.getInstance();
		Date date=new Date();
		calendar.setTime(date);
		int day=date.getDay();
		calendar.set(calendar.DATE, day-1);
		String yesterday=new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		System.out.println("时间为："+calendar.getTime()+"昨天日期为："+yesterday);
		
		return null;
	}
	
	
	
	
	
	
	
	//根据日期查询发表博客数量
	@PostMapping("/selectBlogListByDate")
	@ResponseBody
	public Blogs selectBlogListByDate(HttpServletRequest request) throws ParseException
	{
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		
		List<String> days=new ArrayList<String>();
		List<Long> counts=new ArrayList<Long>(); 
		Date date=new Date();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		long nd=1000*24*60*60;
		Blogs blogs=new Blogs();
		String startTime=request.getParameter("startTime");
		String endTime=request.getParameter("endTime");
		Date start=simpleDateFormat.parse(startTime);
		Date end=simpleDateFormat.parse(endTime);
		long num=(end.getTime()-start.getTime())/nd;
		for(long i=num;i>0;i--)
		{
			calendar.set(calendar.DATE, (int) (date.getDate()-i));
			days.add(simpleDateFormat.format(calendar.getTime()));
		}
		days.add(simpleDateFormat.format(date));
		calendar.set(calendar.DATE, date.getDate()+1);
		days.add(simpleDateFormat.format(calendar.getTime()));
		
		for(long i=0;i<=num;i++)
		{
			counts.add(blogService.getBlogCountByDate(1,days.get((int) i), days.get((int) (i+1))));
		}	
		blogs.setCount(counts);
		blogs.setDays(days);
		blogs.setNum(num+1);
		
		return blogs;
	}
	
	
	//获取相应的博客，可以获取所有的博客，也可以根据种类（类别、状态）来查询博客
	//这里是要设置一个参数的，用于根据什么类型的获取博客数组，但是这里先设置获取所有的博客，后面的后面再设置
	//这里要获取
	@GetMapping("/selectGroupLikeBlogTableByPage")
	@ResponseBody
	public Page<Blog> selectGroupLikeBlogTableByPage(HttpServletRequest request)
	{
		System.out.println("进入测试页面");
		PageInfo pageInfo=new PageInfo();
		//页面大小
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		int pageNumber=Integer.parseInt(request.getParameter("page"));
		int status=Integer.parseInt(request.getParameter("status"));
		System.out.println("pageSize为："+pageSize+" pageNum为："+pageNumber+" 状态为： "+status);
		Sort sort=new Sort(Direction.DESC, "blog_id");
		Pageable pageable=new PageRequest(pageNumber-1, pageSize, sort);
		Page< Blog> pages = null;
		
		switch (status) {
		//查询草稿
		case -1:
			pages=blogService.getBlogsByStatus(status, pageable);
			break;
		//查询发布
		case 1:
			pages=blogService.getBlogsByStatus(status, pageable);
			break;
		
		//查询垃圾箱
		case 2:
			pages=blogService.getBlogsByStatus(status, pageable);
			break;
			
		//查询推荐
		case 3:
			pages=blogService.getBlogsByRecommend(2,pageable);
			break;
		
		//查询置顶
		case 4:
			pages=blogService.getBlogsByTop(2, pageable);
			break;
		
		//查询所有
		case 5:
			Sort sort2=new Sort(Direction.DESC,"blogid");
			Pageable pageable2=new PageRequest(pageNumber-1, pageSize, sort2);
			pages=blogService.getAllBlogs(pageable2);
			break;

		//按照类别来查询
		case 6:
			long typeid=Long.parseLong(request.getParameter("typeId"));
			
			System.out.println(typeid);
			pages=blogService.getBlogsByTypeId(typeid,pageable);
			break;
		default:
			break;
		}
		System.out.println(pages.getTotalPages());
		
		return pages;
		
	}
	
	//根据具体某页查询博客，返回的是某页的博客数据
	//根据博客的关键字动态的查询博客
	@MyLog(value="查询博客")
	@GetMapping("/selectBlogListByPage")
	@ResponseBody
	public Page<Blog> selectBlogListByPage(HttpServletRequest request)
	{
		String keyWord=request.getParameter("keyword");
		System.out.println("keyword的值为："+request.getParameter("keyword"));
		int page=Integer.parseInt(request.getParameter("page"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		Sort sort=new Sort(Direction.DESC,"blog_id");
		Pageable pageable=new PageRequest(page-1, pageSize,sort);
		Page<Blog>blogs=blogRepository.getBlogsByStatusForPage(1,keyWord, pageable);
		System.out.println(page+""+pageSize) ;
		return blogs;
	}
	//根据具体某页查询博客，没有关键字，返回的是某页的博客数据
	@GetMapping("/getBlogListBySpecifitPage")
	@ResponseBody
	public Page<Blog> getBlogListByPage(HttpServletRequest request)
	{
		int status=Integer.parseInt(request.getParameter("status"));
		int page=Integer.parseInt(request.getParameter("page"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		Sort sort=new Sort(Direction.DESC,"blog_id");
		Pageable pageable=new PageRequest(page-1, pageSize,sort);
		switch (status) {
		case 1:
			return blogService.getBlogByStatusForPart(1, pageable);
		
			//获取指定页的置顶热门的的博客
		case 2:
			return blogService.getBlogByTopForPart(2, pageable);
			
		//获取指定页的推荐的博客
		case 3:
			return blogService.getBlogByRecommendForPage(2, pageable);
		
		//根据typeid获取具体某页的博客
		case 4:
			long typeId=Long.parseLong(request.getParameter("typeId"));
			return blogService.getBlogByTypeIdForOnePage(typeId, pageable);
		
		case 5:
			Sort sort2=new Sort(Direction.DESC,"page_view");
			Pageable pageable2=new PageRequest(page-1, pageSize,sort2);
			return blogService.getBlogByStatusForPart(1, pageable);

		//根据关键字获取具体某页的博客
		case 6:
			String keyWord=request.getParameter("keyWord");
			
			return blogRepository.getBlogsByStatusForPage(1, keyWord, pageable);
		default:
			break;
		}
		return null;
	}
	
	
	
	//根据博客id查询博客，返回一个blog对象
	@GetMapping("/selectBlogById")
	@ResponseBody
	public Blogs selectBlogById(HttpServletRequest request,Model model)
	{
		long id=Long.parseLong(request.getParameter("id"));
		System.out.println(id);
		Blogs blogs=new Blogs();
		Blog blog=blogService.getBlogById(id);
		blogs.setBlog(blog);		
		long typeId=blogService.getBlogTypeIdById(id);
		blogs.setBlogTypeId(typeId);
		List<Picture> pictures=new ArrayList<Picture>();
		pictures=pictureService.getPictureByName(blog.getPicturePath());
		if(pictures.size()>0)
		{
			long pictureId=pictures.get(0).getId();
			System.out.println("图片id为："+pictureId);
			blogs.setPictureId(pictureId);
			
		}
		else
		{
			blogs.setPictureId(0);
		}
		
		return blogs;
	}
	
	
	//查询博客所有状态，返回的是三个状态的博客集合
	//已修改
	@GetMapping("/selectBlogListByStatus")
	@ResponseBody
	public List<Integer> selectBlogListByStatus()
	{
		
		return blogService.getAllBlogStatus();
	}
	
	//根据点击量排行获取前7个博客点击量的数量
	@GetMapping("/selectBlogByClick")
	@ResponseBody
	public List<Long> selectBlogByClick()
	{
		
		List<Blog> blogs=blogService.getBlogsByPageView();
		List<Long> count=new ArrayList<Long>();
		for(int i=0;i<7;i++)
		{
			count.add(blogs.get(i).getPageView());
		}
		return count;
	}
	
	
}
