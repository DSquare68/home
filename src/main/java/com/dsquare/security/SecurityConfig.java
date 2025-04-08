package com.dsquare.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;

import com.dsquare.page.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Authentication authen =
		//SecurityContextHolder.getContext().getAuthentication();
		http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
		super.configure(http);
		setLoginView(http, LoginView.class);
	}
	 @Override
	    public void configure(WebSecurity web) throws Exception {
	        // Customize your WebSecurity configuration.
	        super.configure(web);
	    }
}