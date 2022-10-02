package com.ProyectoEmpresarial.app.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.naming.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CustomAuthenticactionFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;



	public CustomAuthenticactionFilter(AuthenticationManager authenticationManager) {

		this.authenticationManager=authenticationManager;
	}

@Override
public Authentication attemptAuthentication(HttpServletRequest request , HttpServletResponse response){

	String username=request.getParameter("correo");
	String password=request.getParameter("contraseña");
	UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(username,password,new ArrayList<>());




return authenticationManager.authenticate( authenticationToken);
}

@Override
protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
		Authentication authResult) throws IOException, ServletException {

	User user=(User) authResult.getPrincipal();



	Algorithm algoritmo = Algorithm.HMAC256("secret".getBytes());
	String access_token=JWT.create().withSubject(user.getUsername())
			.withExpiresAt(new Date(System.currentTimeMillis()+1000 * 61 * 60))
			.withIssuer(request.getRequestURL().toString()).withIssuer(request.getRequestURL().toString())
			.withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
					.collect(Collectors.toList())).sign(algoritmo);
	String refrest_Token=JWT.create().withSubject(user.getUsername()).withExpiresAt(new Date(System.currentTimeMillis()+1*60*1000))
			.withIssuer(request.getRequestURI().toString()).sign(algoritmo);
Map<String, String>tokens=new HashMap<>();
tokens.put("access_token", access_token);
tokens.put("refrest_Token", refrest_Token);
response.setContentType(MediaType.APPLICATION_JSON_VALUE);
new ObjectMapper().writeValue(response.getOutputStream(), tokens);



}



}
