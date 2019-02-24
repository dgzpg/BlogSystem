package com.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.application.Entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{
	
	@Query(value="select * from admin  where count_id=?1",nativeQuery=true)
	public List<Admin> getAdminByCountId(String countId);

}
