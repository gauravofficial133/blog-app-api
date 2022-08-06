package com.blog.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String requestToken = request.getHeader("Authorization");
		
		String username = null;
		
		String token = null;
		
		if (requestToken != null && requestToken.startsWith("Bearer")) {
			
			token = requestToken.substring(7);
			
			try {
				username = this.jwtTokenHelper.getUserNameFromToken(token);
				
			} catch (IllegalArgumentException illegalArgumentException) {
				
				System.out.println("Unable to get jwt token");
				
			} catch(ExpiredJwtException expiredJwtException) {
				
				System.out.println("Token expired");
			
			} catch(MalformedJwtException malformedJwtException) {
			
				System.out.println("Invalid Jwt");
			
			}
			
		}else {
			
			System.out.println("Jwt does not start with Bearer");
		}
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			
			if(this.jwtTokenHelper.validateToken(token, userDetails)) {
			
				UsernamePasswordAuthenticationToken userNamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				
				userNamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(userNamePasswordAuthenticationToken);
			
			}else {
			
				System.out.println("Invalid JWT Token");
			
			}
		
		}else {
		
			System.out.println("Username is null");
		
		}
		
		filterChain.doFilter(request, response);
		
	}
	
}
