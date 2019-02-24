package com.application.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value="ResourceService")
public interface ResourceClient {
	@RequestMapping(value="selectResourceListByStatus",method=RequestMethod.GET)
	public String getAllResourceCount() ;

}
