package com.example.movice_ticket.config;

import com.example.movice_ticket.dao.impl.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserSecurityService userSecurityService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		 auth.userDetailsService(userSecurityService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests()
		 .antMatchers("/","/role","/welcome","/error","/static/**").permitAll()
		 .antMatchers("/user/**").hasAnyRole("USER","ADMIN")
		 .antMatchers("/admin/**").hasRole("ADMIN")
		 .and()
		 .formLogin().loginPage("/login").defaultSuccessUrl("/role")
		 .failureForwardUrl("/to/error")

		 //自定义参数
		 .usernameParameter("userName").passwordParameter("userPassword")
		 .and()
		 //退出系统
		 .logout().logoutUrl("/logout").logoutSuccessUrl("/to/login") ;
		 http.headers().frameOptions().disable();
	}
	
}
