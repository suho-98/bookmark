package com.min.edu.config;

import java.io.IOException;
import java.sql.Date;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// TODO 009 요청필터당 한번싹 호출하여 요청시 필터를 활성화 하기 위해서 OncePerRequestFilter
public class JWTFilter extends OncePerRequestFilter {

	@Autowired
	private JWService jwtService;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// client로부터 토큰을 받는다

		
	}

	//TODO 010 token으로 사용자 이름을 추출 sub : claim에서  getSubject에서 추출
	public String extractUseName(String token) {
		return extractClaim(token)
	}
	//TODO 013 사용자가 인증상태 확인 (토큰 만료 확인)
	public boolean validateToken(String token, UserDetails userDetails);
	
	//TODO 014 JWT 토큰이 만료 되었는지 확인 하는 메소드(시간측정)
	private boolean isTokenExpiration(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	//TODO 015 JWT에서 만료시간을 추출하는 메소드
	prDatee Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
}
