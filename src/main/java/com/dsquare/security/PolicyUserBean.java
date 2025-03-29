package com.dsquare.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "pl.business.cat.security")
public class PolicyUserBean {
	
	@Bean
	public PolicyUserAuthorizationManager getPolicyUserAuthorizationManager() {
		return new PolicyUserAuthorizationManager("ADMIN");
	}

}
