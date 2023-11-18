package com.carrentalsystem.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.carrentalsystem.main.service.UserService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserService userService; 
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("configure....called...");
		 auth.authenticationProvider(getProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http
		 .authorizeRequests()
		 .antMatchers("/admin/signup","/host/signup","/customer/signup","/admin/getone/{id}","/admin/getall",
				 "/admin/delete/{id}","/admin/update/{id}","/add/{cid}/{carid}","/customer/update/{cid}",
				 "/host/update/{hid}","/host/delete/{id}","car/delete/{hid}/{carid}").permitAll()
		 .antMatchers(HttpMethod.GET,"/user/login").authenticated()
		 .anyRequest().authenticated()
		 .and().httpBasic()
		 .and()
		 .csrf().disable()
		 .cors().disable();
		 //Next: Role Based Access
	}
	
	@Bean
	public PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}
	public DaoAuthenticationProvider getProvider() {
		System.out.println("getProvider....called....");
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		//also, I want spring to know that I have encrypted password in the db
		dao.setPasswordEncoder(getEncoder());
		dao.setUserDetailsService(userService);  //UserDetailsService : UserService
		return dao; 
	}

}
