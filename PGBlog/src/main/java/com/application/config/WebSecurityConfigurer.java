package com.application.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.session.ConcurrentSessionFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter{

	@Resource
	private SessionRegistry seRegistry;
	
	@Bean
	public SessionRegistry sessionRegistry()
	{
		return new SessionRegistryImpl();
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
	
		//允许用户访问系统中子页面
		http.csrf().disable().headers().frameOptions().sameOrigin();
		
	/*	http.authorizeRequests()
				.antMatchers("/login").permitAll()
				.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/login").permitAll()
			.and()
			.logout()
				.permitAll();*/
				
		
		
		
		http.sessionManagement().invalidSessionUrl("/sessionOut");
		
		http.sessionManagement().maximumSessions(1).sessionRegistry(seRegistry).expiredUrl("/log");
	}
	
	
	@Autowired
	  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth
	      .inMemoryAuthentication()
	        .withUser("user").password("password").roles("USER");
	  }
}
