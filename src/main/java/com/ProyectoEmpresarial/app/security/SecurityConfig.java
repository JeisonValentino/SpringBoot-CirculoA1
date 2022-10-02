package com.ProyectoEmpresarial.app.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import lombok.RequiredArgsConstructor;


@Configuration @EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
private final UserDetailsService userDetailsService ;
private final BCryptPasswordEncoder bCryptPasswordEncoder ;


public SecurityConfig (UserDetailsService userDetailsService ,BCryptPasswordEncoder bCryptPasswordEncoder) {
	this.userDetailsService=userDetailsService;
this.bCryptPasswordEncoder=bCryptPasswordEncoder;
}



	@Override
	protected void configure(AuthenticationManagerBuilder auth)throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);




	}



	@Override
	protected void configure(HttpSecurity http)throws Exception{
		CustomAuthenticactionFilter customAuthenticactionFilter=new CustomAuthenticactionFilter(authenticationManagerBean());
		customAuthenticactionFilter.setFilterProcessesUrl("/System/login");

		 CorsConfiguration corsConfiguration = new CorsConfiguration();
	        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
	        corsConfiguration.setAllowedOrigins(List.of("*"));
	        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT","OPTIONS","PATCH", "DELETE"));
	        corsConfiguration.setAllowCredentials(true);
	        corsConfiguration.setExposedHeaders(List.of("Authorization"));

		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.cors().and().authorizeRequests().antMatchers("/System/login/**","/users/token/refresh/**", "/").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/user/**");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/user/save/**");
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(customAuthenticactionFilter);
		http.addFilterBefore(new CustomAuthorizationFilter(),  UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean()throws Exception {
		return super.authenticationManagerBean();
	}




}
