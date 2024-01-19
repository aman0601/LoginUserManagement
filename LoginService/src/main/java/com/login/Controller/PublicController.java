package com.login.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public-api")
public class PublicController {

	@GetMapping("/hello")
	public String publicHello() {
		return "Hello from Public API!";
	}
}
