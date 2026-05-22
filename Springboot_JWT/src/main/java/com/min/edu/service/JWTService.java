package com.min.edu.service;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

//TODO 007 JWT를 생성하는 서비스를 작성
@Service
public class JWTService {
	
	//임의의 secretkey
	private String secretkey;
	
	//keyGenerator를 통해서  secretkey
	public JWTService() {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk = keyGen.generateKey();
			Secretkey = Base64.getEncoder().encodeToString(sk.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//연습용 JWT를 사용 하겠다
	public String generateToken(String username) {
		//이름,만료일 등을 통한 정보 입력
		//Map으로 생성후 jwts.claims를 통해서 반환
		//****0.12.xx이전 버전에서는 setClaims를 통해서 생성, 이후에서는 Builder Pattern
		Map<String, Object>claims = new HashMap<String, Object>();
		return Jwts.builder()
				.claims() //claim은 JWT본문에 들어가는 데이터 정보(사용자정보)
				.add(Claims)
				.subject(usename)//인증된 사용자의 username을 대상을 함
				.issuedAt(new Date(System.currentTimeMillis()))//발급한 시간
				.expiration(new Date(System.currentTimeMillis()+(60*60*10))) //JWT의 만료 시간
	            .and()
	            .signWith(getk)
	}
 
	
	
}
