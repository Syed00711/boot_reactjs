package com.cerner.avaya.rad.env.cloud;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProvider;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;

import com.cerner.avaya.rad.security.HttpSecurityConfigurer;

@Configuration
@EnableOAuth2Sso
@Profile("cloud")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

	// property set by spring-cloud-sso-connector
	@Value("${ssoServiceUrl:placeholder}")
	private String ssoServiceUrl;

	@Value("${security.oauth2.client.clientId:placeholder}")
	private String clientId;

	@Bean
	public AccessTokenProvider accessTokenProviderChain() {
		return new AccessTokenProviderChain(
				Arrays.<AccessTokenProvider>asList(new AuthorizationCodeAccessTokenProvider()));
	}

	@Bean
	public OAuth2RestTemplate oauth2RestTemplate(OAuth2ProtectedResourceDetails oa2prd, OAuth2ClientContext oa2cc) {
		OAuth2RestTemplate oauth2RestTemplate = new OAuth2RestTemplate(oa2prd, oa2cc);
		oauth2RestTemplate.setAccessTokenProvider(accessTokenProviderChain());
		return oauth2RestTemplate;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		HttpSecurityConfigurer.configure(http);
	}
}
