package com.carrentalsystem.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.CrossOrigin;

import com.carrentalsystem.main.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = {"http://localhost:3000"})
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
		 
		 .cors()
		 .and()
		 .authorizeRequests()
		 
		 .antMatchers(HttpMethod.POST,"/customer/signup").permitAll()
		 .antMatchers(HttpMethod.POST,"/auth/login").permitAll()
		 .antMatchers("/car/getall").permitAll()
		 .antMatchers("/car/getcars/bysource/{source}").permitAll()
		 .antMatchers("/car/get/availablecars/{source}").permitAll()
		 .antMatchers("/bookcar/{cid}/{carid}").permitAll()
		 .antMatchers("/customer/bookings/{cid}").permitAll()
		 .antMatchers("/customer/getone/{id}").permitAll()
		 .antMatchers("/customer/update/{cid}").permitAll()
		 .antMatchers("/car/getone/{carid}").permitAll()
		 .antMatchers("/car/getall/{hid}").permitAll()
		 .antMatchers(HttpMethod.POST,"/host/signup").permitAll()
		 .antMatchers("/car/post/{hid}").permitAll()
		 .antMatchers("/car/update/{hid}/{carid}").permitAll()
		 .antMatchers("/host/delete/{hid}/{carid}").permitAll()
		 .antMatchers("/host/customers/{hid}/{carid}").permitAll()
		 .antMatchers("/host/getone/{hid}").permitAll()
		 .antMatchers("/host/update/{hid}").permitAll()
		 .antMatchers(HttpMethod.POST,"/admin/signup").permitAll()
		 .antMatchers("/admin/getall/hosts").permitAll()
		 .antMatchers("/admin/getall/customers").permitAll()
		 .antMatchers("/customer/bookings/{cid}").permitAll()
		 
		 .antMatchers("/admin/customers/{carid}").permitAll()
		 .antMatchers("/admin/getall/carsbyhost/{hid}").permitAll()
		 .antMatchers("/admin/getone/{id}").permitAll()
		 .antMatchers("/admin/update/{id}").permitAll()
		 
		 
		 
		 .anyRequest().authenticated()
		 .and().httpBasic()
		 .and()
		 .csrf().disable();
		 
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
	@Bean
	public Logger getLogger() {
		return LoggerFactory.getLogger("Log Records");
	}

}
