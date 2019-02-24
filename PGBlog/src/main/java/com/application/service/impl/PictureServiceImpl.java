package com.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.Entity.Picture;
import com.application.dao.PictureDao;
import com.application.service.PictureService;

@Service
public class PictureServiceImpl implements PictureService{

	@Autowired
	private PictureDao pictureDao;
	
	
	//保存图片
	public boolean savePicture(Picture picture) {
		
		return pictureDao.savePicture(picture);
	}

	
	//删除图片
	public boolean deletePicture(Picture picture) {
		// TODO Auto-generated method stub
		return pictureDao.deletePicture(picture);
	}
	
	
	//获取所有图片
	public List<Picture> getAllPicture()
	{
				
		return pictureDao.getAllPicture();
	}
	
	//根据图片id获取图片
	public Picture getPictureById(long id)
	{
		return pictureDao.getPictureById(id);
	}

	//根据图片地址名获取图片
	public List<Picture> getPictureByName(String path)
	{
		return pictureDao.getPictureByName(path);
	}
}
