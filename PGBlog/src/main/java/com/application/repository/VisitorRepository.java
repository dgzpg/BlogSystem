package com.application.repository;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.application.Entity.Visitor;

public interface VisitorRepository extends JpaRepository<Visitor, Long>{

	//获取所有的访客
	@Query(value="select * from visitor order by ?#{#pageable}",countQuery="select count(*) from visitor",nativeQuery=true)
	public Page<Visitor> getAllVisitors(Pageable pageable);
	
	//获取所有访客的数量
	@Query(value="select count(*) from visitor",nativeQuery=true)
	public long getAllVisitorCount();
	
	//根据ip进行模糊匹配
	@Query(value="select * from visitor where ip like CONCAT('%',?1,'%') order by ?#{#pageable}",countQuery="select count(*) from visitor",nativeQuery=true)
	public Page<Visitor> getVisitorForPageByIp(String ip,Pageable pageable);
	
	//根据日期进行查询，返回所有的记录
	@Query(value="select * from visitor where visit_time between ?1 and ?2 order by ?#{#pageable}",countQuery="select count(*) from visitor",nativeQuery=true)
	public Page<Visitor> getVisitorForPageByDate(String startTime,String endTime,Pageable pageable);
	
	//根据日期进行查询，返回的是具体某页的内容
	@Query(value="select * from visitor where visit_time between ?1 and ?2",countQuery="select count(*) from visitor",nativeQuery=true)
	public Page<Visitor> getVisitorForSpecificPageByDate(String startTime,String endTime,Pageable pageable);
	
	//根据日期进行查询，返回的是相应时间段的所有访客的数量
	@Query(value="select count(*) from visitor where visit_time between ?1 and ?2",nativeQuery=true)
	public long getVisitorListByDate(String startDate,String endDate);
	
	//根据地区进行模糊匹配
	@Query(value="select * from visitor where area like CONCAT('%',?1,'%') order by ?#{#pageable}",countQuery="select count(*) from visitor",nativeQuery=true)
	public Page<Visitor> getVisitorForPageByArea(String area,Pageable pageable);
	
	//根据浏览器进行匹配
	@Query(value="select * from visitor where browser like CONCAT('%',?1,'%') order by ?#{#pageable}",countQuery="select count(*) from visitor",nativeQuery=true)
	public Page<Visitor> getVisitorForPageByBrowser(String broswer, Pageable pageable);
	
	//根据系统进行匹配
	@Query(value="select * from visitor where system like CONCAT('%',?1,'%') order by ?#{#pageable}",countQuery="select count(*) from visitor",nativeQuery=true)
	public Page<Visitor> getVisitorForPageBySystem(String system,Pageable pageable);
	
	//根据不同状态获取所有的访客
	@Query(value="select * from visitor where status=?1",nativeQuery=true)
	public List<Visitor> getVisitorForListByStatus(int status);
	
	//根据访客的身份状态（管理员，普通用户）进行查询，返回的是具体的某页结果
	@Query(value="select * from visitor where status=?1",countQuery="select count(*) from visitor where status=?1",nativeQuery=true)
	public Page<Visitor> getVisitorByStatusForSpecificPage(int status,Pageable pageable);
	
	//根据访客的身份状态以及操作的类别进行查询，返回的是具体某页的结果
	@Query(value="select * from visitor where status=?1 and ope_object=?2",countQuery="select count(*) from visitor where status=?1 and ope_object=?2",nativeQuery=true)
	public Page<Visitor> getVisitorByStatusAndOpeObjectForSpecificPage(int status,int opeObject,Pageable pageable);
	
	//根据ip进行查询，返回的是具体某页的结果
	@Query(value="select * from visitor where ip like concat('%',?1,'%')",countQuery="select count(*) from visitor",nativeQuery=true)
	public Page<Visitor> getVisitorByIpForSpecificPage(String ip,Pageable pageable);
	
	
}
