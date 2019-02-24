package com.application.dao;

import java.util.List;

import com.application.Entity.Picture;

public interface PictureDao {

	//保存图片
	public boolean savePicture(Picture picture);
	
	//删除图片
	public boolean deletePicture(Picture picture);
	
	//获取所有图片
	public List<Picture> getAllPicture();
	
	//根据图片id获取图片
	public Picture getPictureById(long id);
	
	//根据图片地址名获取图片
	public List<Picture> getPictureByName(String path);
}
