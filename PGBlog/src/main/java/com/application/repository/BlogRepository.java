package com.application.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.application.Entity.Blog;
import com.application.Entity.BlogType;


public interface BlogRepository extends JpaRepository<Blog, Long> {

	@Query(value="select blog from blog where blog_id=?1",nativeQuery=true)
	public Blog getBlogsById(long id);
	
	//查询所有的博客，按照时间的降序排序,时间顺序就是id的顺序，时间越近，id越大
	//已修改
	@Query(value="select * from blog order by ?#{#pageable}",countQuery="select count(*) from blog",nativeQuery=true)
	public Page<Blog> getAllBlogs(Pageable pageable);
	
	
	//根据博客状态和关键字进行查询博客，结果为根据具体某页查询的集合
	//已修改
	@Query(value="select * from blog  where status =?1 and key_word like CONCAT('%',?2,'%')",countQuery="select count(*) from blog where status=?1",nativeQuery=true)
	public Page<Blog> getBlogsByStatusForPage(int status,String key_word,Pageable pageable);
	
	//根据博客状态进行查询，结果为所有的查询的结果
	@Query(value="select * from blog  where status =?1 order by ?#{#pageable}",countQuery="select count(*) from blog where status=?1",nativeQuery=true)
	public Page<Blog> getBlogsByStatus(int status,Pageable pageable);
	
	//根据博客的三种状态获取博客的具体某页的数据
	//已修改
	@Query(value="select * from blog where status=?1",countQuery="select count(*) from blog where status=?1",nativeQuery=true)
	public Page<Blog> getBlogByStatusForPart(int status,Pageable pageable);
			
	//根据博客置顶来获取博客的具体某页的数据
	//已修改
	@Query(value="select * from blog where top=?1",countQuery="select count(*) from blog where top=?1",nativeQuery=true)
	public Page<Blog> getBlogByTopForPart(int top, Pageable pageable);
			
	//根据博客推荐来获取博客的具体某页的数据
	//已修改
	@Query(value="select * from blog where recommend=?1",countQuery="select count(*) from blog where recommend=?1",nativeQuery=true)
	public Page<Blog> getBlogByRecommendForPart(int recommend,Pageable pageable);
	
	//根据博客的类别明获取具体某页的数据
	//已修改
	@Query(value="select * from blog where type=?1",countQuery="select count(*) from blog where type=?1",nativeQuery=true)
	public Page<Blog> getBlogsByTypeNameForPage(String typeName,Pageable pageable);
	
	//根据博客状态查询所有博客，返回一个list集合
	//已修改
	@Query(value="select * from blog b where b.status=?1",nativeQuery=true)
	public List<Blog> getBlogListByStatus(int status);
	
	//修改博客类型的时候修改博客的类别名
	@Transactional
	@Modifying(clearAutomatically=true)
	@Query(value="update blog set type=?2 where type_id=?1",nativeQuery=true)
	public int updateBlogTypeName(long typeId,String typeName);
	
	
	
	//根据博客的类别名进行查询
	@Query(value="select * from blog b where b.type =?1",nativeQuery=true)
	public Set<Blog> getBlogsByTypeName(String typeName);

	
	//根据博客类别id查询博客
	//已修改
	@Query(value="select * from blog  where type_id=?1 order by ?#{#pageable}",countQuery="select count(*) from blog where type_id =?1",nativeQuery=true)
	public Page<Blog> getBlogsByTypeId(long typeId,Pageable pageable) ;
	
	
	//根据博客类别的id获取具体某页的内容
	//已修改
	@Query(value="select * from blog where type_id=?1",countQuery="select count(*) from blog where type_id=?1",nativeQuery=true)
	public Page<Blog> getBlogByTypeIdForOnePage(long typeId,Pageable pageable);
	
	//根据博客推荐来进行查询博客
	//已修改
	@Query(value="select * from blog  where recommend =?1 order by ?#{#pageable}",countQuery="select count(*) from blog where recommend =?1" ,nativeQuery=true)
	public Page<Blog> getBlogsByRecommend(int recommend,Pageable pageable);
	
	//根据博客是否置顶来查询博客
	//已修改
	@Query(value="select * from blog  where top =?1 order by ?#{#pageable}",countQuery="select count(*) from blog where top =?1",nativeQuery=true)
	public Page<Blog> getBlogsByTop(int top,Pageable pageable);
	
	/*//根据博客的关键字来查询博客
	@Query(value="select * from blog b where b.key_word =?1",nativeQuery=true)
	public Set<Blog> getBlogsByKeyWord(String keyWord);*/
	
	
	
	

	//根据发表的时间段来查询博客,按天数来查询
	@Query(value="select * from blog  where published_time between ?1 and ?2",nativeQuery=true)
	public List<Blog> getBlogsByDate(String firstTime,String lastTime);
	
	//根据发表时间来查询博客，返回博客数量
	@Query(value="select count(*) from blog where status=?1 and published_time between ?2 and ?3",nativeQuery=true)
	public long getBlogCountByDate(int status,String startTime,String endTime);
	

	
	//根据浏览量进行倒序排序
	@Query(value="select * from blog order by page_view desc ",countQuery="select count(*) from blog",nativeQuery=true)
	public List<Blog> getBlogsByPageView();
	
	//根据博客的点击量进行倒序排序并进行分页
	/*@Query(value="select * from blog order by ?#{pageable}",nativeQuery=true)
	public Page<Blog> getBlogsByCommentNum(Pageable pageable);*/
	
	//根据博客id查询博客类别id
	//已修改
	@Query(value="select type_id from blog where blog_id=?1",nativeQuery=true)
	public long getBlogTypeIdById(long id);
}
