package com.example.demogit.ctrl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public String getMethodName(@RequestParam String param) {
	return "좋은아침 입니다.";
}
}	
