package com.example.demo;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.dao.user_dao;

@Component
public class JWTFilter extends OncePerRequestFilter {
	
	Logger log = Logger.getAnonymousLogger();

	@Autowired
	private JWTUtility util;
	
	@Autowired
	private user_dao userdao;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String auth =request.getHeader("Authorization");
		String token = null;
		String username = null;
		
		if(null!=auth && auth.startsWith("Bearer")) {
			log.info("Token ---> " + auth);
			token = auth.substring(7);
			log.info("Token Sub ---> " + token);
			username = util.getUsernameFromToken(token);
			log.info(username);
		}
		
		if(null!=username  && SecurityContextHolder.getContext().getAuthentication() == null) {
			com.example.demo.UserDetails userDetails = userdao.loadUserByUsername(username);
			if(util.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				 SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		
		filterChain.doFilter(request, response);
		
	}

}
