package com.szte.recommender.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConf extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return super.userDetailsService();
	}
	
	@Autowired
	private UserDetailsService userService;
	
	//felhasznalok letrehozasara alkalmazhato
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
//				.antMatchers("/users/**").hasRole("ADMIN")
				.antMatchers("/users/showFormForAdd").permitAll()
				.antMatchers("/users/save").permitAll()
				.antMatchers("/registration").permitAll()
				.antMatchers("/reg").permitAll()
				.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/login").permitAll()
				.defaultSuccessUrl("/index", true)
			.and()
			.logout()
				.logoutSuccessUrl("/login?logout").permitAll();
	}

	
	
}
