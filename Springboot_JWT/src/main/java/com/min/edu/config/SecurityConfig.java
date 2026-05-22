package com.min.edu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		   return http.csrf(csrf -> csrf.disable()) //csrf비활성화
				   .authorizeHttpRequests(request -> request
						   .requestMatchers("/register").permitAll() // requestMatchers는 인증을 
						   .anyRequest().authenticated()) //모든 요청을 인증처리
				   .sessionManagement(session -> 
				                        session);
	}
	
	
	//비밀번호를 암호화하고 판단할때 사용하는 PasswordEncoder 오버라이드해서 암호화 되어있지 않은 값을 사용할 수 있도록 했다.
	//@Bean
	//public PasswordEncoder loginPassword() {
	//	return new NoPasswordEncoder();
	}

//비밀번호 암호화를 통해서 로그인을 처리
@Bean
public PasswordEncoder loginPassword() {
	return new BCryptPasswordEncoder();
}
	//TODO UserDetailsService가 자동으로 AuthenticationManager를 구성해 주지만 세부적인 인증을 위해서 생성함
@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) {
	return configuration.getAuthenticationManager();
}
}
