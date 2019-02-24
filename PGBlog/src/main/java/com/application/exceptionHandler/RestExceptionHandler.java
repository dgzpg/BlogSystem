package com.application.exceptionHandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class RestExceptionHandler {


	
	public static final String DEFAULT_ERROR_VIEW = "error";
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus
	public ModelAndView exceptionHandler(Exception e,HttpServletRequest request)throws Exception
	{
		System.out.println(e.getMessage());
		ModelAndView view=new ModelAndView();
		view.addObject("exception",e.getMessage());
		view.addObject("url", request.getRequestURI());
		view.setViewName(DEFAULT_ERROR_VIEW);
		return view;
	}
	
}
