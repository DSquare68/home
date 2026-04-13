package com.dsquare.security;


//@EnableWebSecurity
//@Configuration
public class SecurityConfig {

	/*
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Authentication authen =
		// SecurityContextHolder.getContext().getAuthentication();
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		super.configure(http);
		// setLoginView(http, LoginView.class);
	}
	*/
	/*
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // allow access to login route, require auth for everything else
		http.with(VaadinSecurityConfigurer.vaadin(), configurer -> {
	        configurer.enableNavigationAccessControl(false);
	        //configurer.loginView(LoginPage.class);   // this is good
	        // Do NOT call enableCsrfConfiguration(false) here
	    });

	    // Do NOT disable CSRF globally

	    http.authorizeHttpRequests(auth -> auth
	        .anyRequest().authenticated()   // you have this for now (good for testing)
	    );

        return http.build();
    }
    */
}