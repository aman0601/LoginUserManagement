package com.login.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private-api")
public class PrivateController {

	@GetMapping("/hello")
	@PreAuthorize("hasRole('USER')") // Adjust role as needed
	public String privateHello() {
		return "Hello from Private API!";
	}
}
