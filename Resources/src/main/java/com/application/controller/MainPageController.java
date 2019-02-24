package com.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

	@GetMapping("/source")
	public String getSource()
	{
		System.out.println("跳转至资源页面！");
		return "resource";
	}
}
