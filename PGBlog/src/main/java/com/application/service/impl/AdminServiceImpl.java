package com.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.Entity.Admin;
import com.application.dao.AdminDao;
import com.application.service.AdminService;


@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDao adminDao;
	
	//验证用户名和密码是否正确
	public Admin checkCountId(String countId) {
		 if(adminDao.getAdminByCountId(countId).size()==0)
		 {
			 return null;
		 }
		 else
		 {
			 return adminDao.getAdminByCountId(countId).get(0);
		 }
	
	}

}
