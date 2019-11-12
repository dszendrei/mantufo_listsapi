package com.mantufo.listsapi.mantufo.listsapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConf extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception{
		BCryptPasswordEncoder encoder = passwordEncoder();
		auth
		  .inMemoryAuthentication()
		    .withUser(System.getenv("mant_user"))
		    .password(encoder.encode(System.getenv("mant_pass")))
		    .roles("ADMIN");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
			.authorizeRequests()
				.antMatchers("/db/renew").hasRole("ADMIN")
				.and()
			.authorizeRequests()
				.antMatchers("/listsdb*")
				.permitAll()
				.and()
			.authorizeRequests()
				.antMatchers("/lists*")
				.permitAll()
				.and()
			.formLogin()
				.loginPage("/db/login")
				.permitAll()
				.successForwardUrl("/db/renew")
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/db/logout"))
				.logoutSuccessUrl("/db/login?logout")
				.permitAll()
			;
	}	
	
}
