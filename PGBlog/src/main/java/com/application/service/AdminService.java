package com.application.service;

import java.util.List;

import com.application.Entity.Admin;

public interface AdminService {

	//验证用户名和密码是否正确
	public Admin checkCountId(String countId);
}
