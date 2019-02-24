package com.application.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.Entity.Admin;
import com.application.dao.AdminDao;
import com.application.repository.AdminRepository;


@Service
public class AdminDaoImpl implements AdminDao {

	@Autowired
	private AdminRepository adminRepository;
	
	//根据管理员账号查询
	public List<Admin> getAdminByCountId(String countId) {
		// TODO Auto-generated method stub
		return adminRepository.getAdminByCountId(countId);
	}

}
