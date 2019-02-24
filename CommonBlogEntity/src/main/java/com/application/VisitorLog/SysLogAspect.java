package com.application.VisitorLog;



import java.lang.reflect.Method;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.application.Entity.Visitor;
import com.application.Entity.VisitorRecord;
import com.application.Util.IpUtil;
import com.application.annotation.MyLog;
import com.application.repository.VisitorRecordRep;
import com.application.repository.VisitorRep;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;





@Aspect
@Component
public class SysLogAspect {
	
	@Autowired
	private VisitorRep visitorRep;
	
	@Autowired
	private VisitorRecordRep visitorRecordRep;
	
	
	

	
	//定义切点@pointcut
	//在注解的位置切入代码
	@Pointcut("@annotation(com.application.annotation.MyLog)")
	public void logPointcut()
	{
		
	}
	
	//切面，配置通知
	@AfterReturning("logPointcut()")
	public  void saveSysLog(JoinPoint joinpoint)
	{
		System.out.println("切面开始。。");
		
		Visitor visitor=new Visitor();
		VisitorRecord visitorRecord=new VisitorRecord();
		
		int opeObject=0;
		String operate="";
		String ip="";
		String params="";
		String area="";
		int status=0;
		
		ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request=attributes.getRequest();
		
		//获取请求参数
		Enumeration<String> paramters=request.getParameterNames();
		int n=0;
		while(paramters.hasMoreElements())
		{
			if(n==0)
			{
				String param=paramters.nextElement();
				//System.out.println(request.getParameter(str));
				params=request.getParameter(param);
				n++;
			}
			if(n==1)
			{
				String user=paramters.nextElement();
				user=request.getParameter(user);
				status=Integer.parseInt(user);
				break;
			}
		}
		System.out.println("参数为："+params+"用户为："+status);
		
		
		//从切面植入点处通过反射机制获取植入点处的方法
		MethodSignature signature=(MethodSignature) joinpoint.getSignature();		
		//获取切入点所在的方法
		Method method=signature.getMethod();
		//获取方法的类名
		String className=joinpoint.getTarget().getClass().getSimpleName();
		String  methodName=method.getName();
		System.out.println("类名为："+className+"方法名为：" +methodName);
		if(status==1)
		{
			if(className.equals("MainPageManager")||className.equals("BlogManager"))
			{
				opeObject=1;
			}
			else if(className.equals("BlogTypeManager"))
			{
				opeObject=2;
			}
			else if(className.equals("ResourceManager"))
			{
				opeObject=3;
			}
			else if(className.equals("VisitorManager"))
			{
				opeObject=4;
			}
		}
		else
		{
			//查看博客，根据博客id查询博客
			if(methodName.equals("selectBlogById"))
			{
				opeObject=5;
			}
			//关键字搜索博客
			else if(methodName.equals("getBlogKeyWord"))
			{
				opeObject=6;
			}
			else if(methodName.equals("getSourceSpecificPageByStatus"))
			{
				opeObject=7;
			}
		}
		
		visitor.setOpeObject(opeObject);
		
		//获取操作
		 MyLog myLog = method.getAnnotation(MyLog.class);
		 if(myLog !=null)
		 {
			 //获取操作名
			  operate=myLog.value();
		 }
		
	    
		 //获取请求的ip
		
		  ip = request.getHeader("x-forwarded-for"); 
		 if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
		 {                  
			 ip = request.getHeader("Proxy-Client-IP");              
		}              
		 if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
		 {                 
			 ip = request.getHeader("WL-Proxy-Client-IP");             
			 }              
		 if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			 ip = request.getRemoteAddr();                  
			 if(ip.equals("127.0.0.1")){   
				 //根据网卡取本机配置的IP                       
				 InetAddress inet=null;    
				 try {           
					 inet = InetAddress.getLocalHost(); 
					 } catch (Exception e) {  
						 e.printStackTrace();             
						 }                       
				 ip= inet.getHostAddress();                
				 }             
			 }              // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割            
		 if(ip != null && ip.length() > 15){            
			 if(ip.indexOf(",")>0){   
				 ip = ip.substring(0,ip.indexOf(","));   
				 }               
			 }     	 
		System.out.println("ip 地址为："+ip);
		

	
		
		
		
		//获取操作系统和浏览器等信息

		UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));   //req就是request请求

		Browser browser = userAgent.getBrowser();  //获取浏览器信息 

		OperatingSystem system = userAgent.getOperatingSystem(); //获取操作系统信息

		StringBuffer userInfo = new StringBuffer();

		System.out.println(("操作系统："+system.toString()+" 浏览器："+browser.toString()));
		
		try {
			 area=IpUtil.getIp(ip);
		} catch (Exception e) {
			
			//e.printStackTrace();
			
			System.out.println("没有找到该ip的地址");
			area="无";
		}
		System.out.println("地区为："+area);
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String time=simpleDateFormat.format(new Date());
		
		visitor.setArea(area);
		visitor.setBrowser(browser.toString());
		visitor.setIp(ip);
		visitor.setOpeObject(opeObject);
		visitor.setOperate(operate);
		visitor.setParams(params);
		visitor.setStatus(status);
		visitor.setSystem(system.toString());
		visitor.setVisitTime(time);
		visitorRep.saveAndFlush(visitor);
		
		if(visitorRecordRep.getRecordByIp(ip,status).size()==0)
		{
			visitorRecord.setArea(area);
			visitorRecord.setBrowser(browser.toString());
			visitorRecord.setIp(ip);
			visitorRecord.setStatus(status);
			visitorRecord.setSystem(system.toString());
			visitorRecord.setVisitTime(time);
			visitorRecord.setVisitNum(0);
			visitorRecordRep.saveAndFlush(visitorRecord);
		}
		else
		{
			long id=visitorRecordRep.getRecordByIp(ip,status).get(0).getId();
			VisitorRecord record=visitorRecordRep.findById(id).get();
			record.setVisitNum(record.getVisitNum()+1);
			visitorRecordRep.saveAndFlush(record);
		}
	
	}

}
