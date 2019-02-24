package com.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.application.Entity.VisitorRecord;



public interface VisitorRecordRep extends JpaRepository<VisitorRecord, Long>{

	//根据访客的ip查询访客记录
	@Query(value="select * from visitor_record where ip=?1 and status=?2",nativeQuery=true)
	public List<VisitorRecord> getRecordByIp(String ip,int status);
}
