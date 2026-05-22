package com.min.edu.config;

public class NoPasswordEncoder {

	//로그인 인증시 DB의 정보를 BCrypPasswordEncoder를 사용하지 않기 위한 설정
	//@Nullable Null값이 들어올 수 있다
	//메소드는 반환타입이 null일수 있다
	//parameter null값이 전송될 수 있다
	
	//security의 암호화 하는 메소드
	@Override
	public @Nullable String encode(@Nullable CharSequence rawPassword) {
		return rawPassword.toString()
				
	}
}
