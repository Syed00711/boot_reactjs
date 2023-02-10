package com.cerner.avaya.rad.env.local;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.cerner.avaya.rad.security.HttpSecurityConfigurer;

@Configuration
@Profile("local")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		HttpSecurityConfigurer.configure(http);

		http.formLogin().and().httpBasic();
	}

	@Bean
	UserDetailsService localUserDetailService() throws IOException {
		Properties props = PropertiesLoaderUtils.loadAllProperties("env/local/users.properties");
		return new InMemoryUserDetailsManager(props);
	}
}
