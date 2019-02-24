package com.application.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.application.Entity.Picture;
import com.application.service.PictureService;

@Controller
public class PictureManager {
	
	@Autowired
	private PictureService pictureService;

	//获取所有图片文件
	//返回所有文件数组，其中包含文件的路径
	@GetMapping("/getFileList")
	@ResponseBody
	public List<Picture> getFileList()
	{
		
		
		return pictureService.getAllPicture();
	}
	
	
	//删除图片
	@GetMapping("/deletePic")
	@ResponseBody
	public String deletePic()
	{
		return null;
	}
	
	//上传图片,获取文件，将问价保存在本地，然后在数据库中保存picture的地址和名字等信息
	@PostMapping("/fileupload")
	@ResponseBody
	public Picture fileupload(@RequestParam("file")MultipartFile file,HttpServletRequest request,HttpServletResponse response)
	{
		//System.out.println("测试图片");
		System.out.println(file.getOriginalFilename());
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy—MM-dd HH-mm-ss");
		
		String fileName=simpleDateFormat.format(new Date())+".jpg";
		System.out.println("文件名为："+fileName);
		try {
			File file2=new File("src/main/resources/static/picture/"+fileName);
			System.out.println("文件名为"+file.getOriginalFilename()+"文件类型为："+file.getContentType());
			BufferedOutputStream stream=new BufferedOutputStream(new FileOutputStream(file2));
			stream.write(file.getBytes());
			stream.close();
		} catch (Exception e) {
			
			System.out.println("保存失败！");
		}
		Picture picture=new Picture();
		picture.setPath("http://localhost:9010/picture/"+fileName);
		pictureService.savePicture(picture);
		
		
		return picture;
	}
	
	
	
}
