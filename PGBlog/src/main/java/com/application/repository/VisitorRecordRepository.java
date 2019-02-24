package com.application.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.application.Entity.Visitor;
import com.application.Entity.VisitorRecord;

public interface VisitorRecordRepository extends JpaRepository<VisitorRecord, Long>{
	
		
		//根据ip查询访客
		@Query(value="select * from visitor_record where ip=?1 ",nativeQuery=true)
		public List<VisitorRecord> getRecordByIp(String ip);
		
	
		//获取所有的访客并且分页
		@Query(value="select * from visitor_record  order by ?#{#pageable}",countQuery="select count(*) from visitor ",nativeQuery=true)
		public Page<VisitorRecord> getAllVisitorRecords(Pageable pageable);
		
		//根据ip进行模糊匹配所有访客
		@Query(value="select * from visitor_record  ip like CONCAT('%',?1,'%')  order by ?#{#pageable}",countQuery="select count(*) from visitor  ",nativeQuery=true)
		public Page<VisitorRecord> getVisitorRecordForPageByIp(String ip,Pageable pageable);
		
		//根据ip进行模糊匹配黑名单
		@Query(value="select * from visitor_record where status=?2 and ip like CONCAT('%',?1,'%')  order by ?#{#pageable}",countQuery="select count(*) from visitor where status=?2 ",nativeQuery=true)
		public Page<VisitorRecord> getBlackRecordForPageByIp(String ip,int status,Pageable pageable);
		
		
		//根据日期进行匹配黑名单
		@Query(value="select * from visitor_record where status=?3 and visit_time between ?1 and ?2 order by ?#{#pageable}",countQuery="select count(*) from visitor where status =?3",nativeQuery=true)
		public Page<VisitorRecord> getBlackRecordForPageByDate(String startTime,String endTime,int status,Pageable pageable);
		
		//根据日期匹配所有的访客
		@Query(value="select * from visitor_record where visit_time between ?1 and ?2 order by ?#{#pageable}",countQuery="select count(*) from visitor ",nativeQuery=true)
		public Page<VisitorRecord> getVisitorRecordForPageByDate(String startTime,String endTime,Pageable pageable);
		
		//根据地区进行模糊匹配黑名单
		@Query(value="select * from visitor_record where area like CONCAT('%',?1,'%')  and status =?2 order by ?#{#pageable}",countQuery="select count(*) from visitor where status=?2",nativeQuery=true)
		public Page<VisitorRecord> getBlackRecordForPageByArea(String area,int status,Pageable pageable);
		
		//根据地区进行模糊匹配所有访客
		@Query(value="select * from visitor_record where area like CONCAT('%',?1,'%')   order by ?#{#pageable}",countQuery="select count(*) from visitor ",nativeQuery=true)
		public Page<VisitorRecord> getVisitorRecordForPageByArea(String area,Pageable pageable);
		
		
		//根据浏览器进行匹配黑名单
		@Query(value="select * from visitor_record where  browser like CONCAT('%',?1,'%') and status=?2 order by ?#{#pageable}",countQuery="select count(*) from visitor where status=?2",nativeQuery=true)
		public Page<VisitorRecord> getBlackRecordForPageByBrowser(String broswer,int status, Pageable pageable);
		
		//根据浏览器进行匹配所有访客
		@Query(value="select * from visitor_record where  browser like CONCAT('%',?1,'%')  order by ?#{#pageable}",countQuery="select count(*) from visitor ",nativeQuery=true)
		public Page<VisitorRecord> getVisitorRecordForPageByBrowser(String broswer, Pageable pageable);		
		
		//根据系统进行匹配黑名单
		@Query(value="select * from visitor_record where status=?2 and system like CONCAT('%',?1,'%') order by ?#{#pageable}",countQuery="select count(*) from visitor where status=?2",nativeQuery=true)
		public Page<VisitorRecord> getBlackRecordForPageBySystem(String system,int status,Pageable pageable);
		
		//根据系统匹配所有访客
		@Query(value="select * from visitor_record where system like CONCAT('%',?1,'%') order by ?#{#pageable}",countQuery="select count(*) from visitor ",nativeQuery=true)
		public Page<VisitorRecord> getVisitorRecordForPageBySystem(String system,Pageable pageable);
		
		//根据不同的状态获取所有的访客并进行分页
		@Query(value="select * from visitor_record where status=?1 order by ?#{#pageable}",countQuery="select count(*) from visitor_record where status=?1 ",nativeQuery=true)
		public Page<VisitorRecord > getVisitorByStatusForPage(int status,Pageable pageable);
		
		//根据不同状态获取所有的访客
		@Query(value="select * from visitor_record where status=?1",nativeQuery=true)
		public List<VisitorRecord> getVisitorRecordForListByStatus(int status);
		
		@Query(value="select * from visitor_record ",nativeQuery=true)
		public List<VisitorRecord> getAllRecord();

}
