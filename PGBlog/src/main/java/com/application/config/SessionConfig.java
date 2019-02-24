package com.application.config;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.application.Entity.Admin;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=20)
public class SessionConfig implements HttpSessionListener{

	

	public void sessionCreated(HttpSessionEvent se) {
		String countId=(String) se.getSession().getAttribute("countId");
		
		System.out.println("session  :"+countId);
		
	}

	public void sessionDestroyed(HttpSessionEvent se) {
	
		System.out.println("session已过期，session为："+se.getSession().getId());
	}

}
