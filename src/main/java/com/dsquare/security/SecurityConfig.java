package com.dsquare.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.vaadin.flow.spring.security.VaadinWebSecurity;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Authentication authen =
		// SecurityContextHolder.getContext().getAuthentication();
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		super.configure(http);
		// setLoginView(http, LoginView.class);
	}

}