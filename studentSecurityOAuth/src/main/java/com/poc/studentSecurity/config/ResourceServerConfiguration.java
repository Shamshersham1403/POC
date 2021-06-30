package com.poc.studentSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
	@Autowired
	private DefaultTokenServices tokenServices;



	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenServices(tokenServices);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests()
	            .antMatchers("/actuator/**", "/","/oauth/*").permitAll()
	            .antMatchers("/project/**","/student/**" ).authenticated()//.hasAuthority("student")
	            .and()
	            .authorizeRequests()
	            .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	           ;
//
//		http.authorizeRequests().antMatchers("/actuator/**","/oauth/**").permitAll()
//				.antMatchers("/student/**")
//				.authenticated().antMatchers("/project/**").authenticated()
//				.anyRequest().authenticated().and()
//				.formLogin().permitAll().and().csrf().disable().sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

}