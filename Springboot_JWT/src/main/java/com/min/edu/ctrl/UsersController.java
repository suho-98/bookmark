package com.min.edu.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.min.edu.model.Users;
import com.min.edu.service.UsersService;

@RestController
public class UsersController {

	@Autowired
	private UsersService service;
	
	@PostMapping("/register")
	public Users register(@RequestBody Users user) {
		return service.register(user);
	}
	
	//TODO 003 인증 제공자를 인증관리자를 통해서 처리하게 된다.
	@PostMapping("/login")
	public String login(@RequestBody Users user) {
		System.out.println("로그인 요청 값 : " + user);
		return "success";
	}
}
