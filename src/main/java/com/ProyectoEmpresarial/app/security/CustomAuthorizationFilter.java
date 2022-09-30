package com.ProyectoEmpresarial.app.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter{

	private Logger logger=LoggerFactory.getLogger(CustomAuthorizationFilter.class);
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	if(request.getServletPath().equals("/System/login") || request.getServletPath().equals("/users/token/refresh") ) {

			filterChain.doFilter(request, response);
		}else {
			String authorizationHeader=request.getHeader(AUTHORIZATION);

			if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			try {
			String token=authorizationHeader.substring("Bearer ".length());

			Algorithm algorithm=Algorithm.HMAC256("secret".getBytes());
			JWTVerifier verifier =JWT.require(algorithm).build();
			DecodedJWT decodedJWT=verifier.verify(token);
			String username=decodedJWT.getSubject();
			String[] roles=decodedJWT.getClaim("roles").asArray(String.class);
			Collection<SimpleGrantedAuthority>authorities =new ArrayList<>();
			stream(roles).forEach(role ->{
				authorities.add(new SimpleGrantedAuthority(role));
			});

			UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username, null,authorities);
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			filterChain.doFilter(request,response);

			}catch(Exception exception) {

				response.setStatus(FORBIDDEN.value());
				Map<String, String>error=new HashMap<>();
				error.put("error_message", exception.getMessage());
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), error);

			}


			}else {


				 response.addHeader("Access-Control-Allow-Headers",
		                    "Access-Control-Allow-Origin, Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
		            if (response.getHeader("Access-Control-Allow-Origin") == null)
		                response.addHeader("Access-Control-Allow-Origin", "*");


				filterChain.doFilter(request, response);
			}
		}

	}

}
