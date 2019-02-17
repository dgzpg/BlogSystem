package com.application.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("ResourceService")
public interface ResourceClient {

	//获取所有资源
	@RequestMapping(value="getSourceSpecificPageByStatus",method=RequestMethod.GET)
	public String getAllResources(@RequestParam("pageSize")String pageSize,@RequestParam("page")String page,@RequestParam("content")String content);
	
	
}
