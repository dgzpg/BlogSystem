package com.application.dao;

import java.util.List;

import com.application.Entity.Admin;

public interface AdminDao {

	public List<Admin> getAdminByCountId(String countId);
}
