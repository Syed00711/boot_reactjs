package com.cerner.avaya.rad.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public class HttpSecurityConfigurer {

	public static void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").antMatchers("/login", "/anon").permitAll()
				.antMatchers("/**").hasAnyRole("ADMIN", "USER");
	}
}
