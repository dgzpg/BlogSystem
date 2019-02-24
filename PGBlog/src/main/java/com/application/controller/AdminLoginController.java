package com.application.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.application.Entity.Admin;
import com.application.config.SessionConfig;
import com.application.service.AdminService;
import com.application.service.BlogService;

@Controller
public class AdminLoginController {

	@Autowired
	private AdminService adminService;
	
	@GetMapping("/login")
	public String login()
	{
		return "/blogs/admin/login";
	}
	
	
	///获取登录信息，
	//登录失败给出提示信息
	//登录成功进入主页面
	@PostMapping("/getLogin")
	
	public String getLogin(@RequestParam String username,@RequestParam String password,HttpServletRequest request,Model model)
	{
		//用户名密码验证成功，返回主页面
		Admin admin=adminService.checkCountId(username);
		if(admin!=null)
		{
			System.out.println("用户名为："+admin.getCountId()+"密码为："+admin.getPassword());
			if(admin.getPassword().equals(password))
			{
				HttpSession session=request.getSession();
				String countId=admin.getCountId();
				session.setAttribute("countId", countId);
				
				System.out.println("session id :"+session.getId()+"session的值为："+session.getAttribute("countId"));
				return "redirect:/index";
			}
			else
			{
				model.addAttribute("msg", "用户名密码错误！");
				return "/blogs/admin/login";
			}
			
		}
		//验证失败，给出提示，重新进入登录页面
		else
		{
			model.addAttribute("msg", "不存在该用户名！");
			return "/blogs/admin/login";
		}
		
	}
	
	@GetMapping("/sessionOut")
	public String sessionOut(HttpServletResponse response) throws IOException
	{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.flush();
		out.println("<script>");
		out.println("alert('账号已过期，请重新登录！')");
		out.println("</script>");
		return "blogs/admin/login";
	}
	
	@GetMapping("/logOut")
	public String logOut(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
	
		session.invalidate();
		//System.out.println("session："+session.getAttribute("countId"));
		return "redirect:http://localhost:9040/index";
	}
}
