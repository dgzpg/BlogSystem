package com.application.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.Entity.Picture;
import com.application.dao.PictureDao;
import com.application.repository.PictureRepository;


@Service
public class PictureDaoImpl implements PictureDao{
	@Autowired
	private PictureRepository pictureRepository;

	//保存图片
	public boolean savePicture(Picture picture) {
		try {
			pictureRepository.saveAndFlush(picture);
			return true;
		} catch (Exception e) {
			return false;
		}
	
	}

	
	//删除图片
	public boolean deletePicture(Picture picture) {
		try {
			pictureRepository.deleteById(picture.getId());
			return true;
		} catch (Exception e) {
			return false;
		}
	
	}
	
		//获取所有图片
	public List<Picture> getAllPicture()
	{
			
		return pictureRepository.findAll();
	}
		
	//根据图片id获取图片
	public Picture getPictureById(long id)
	{
		return pictureRepository.findById(id).get();
	}
	
	//根据图片地址获取图片
	public List<Picture> getPictureByName(String path)
	{
		return pictureRepository.getPictureByName(path);
	}
}
