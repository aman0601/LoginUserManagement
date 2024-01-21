package com.login.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.Model.Response.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> adminAccess() {
		return ResponseEntity.ok(new MessageResponse("Admin Content"));
	}

	@GetMapping("/all")
	public ResponseEntity<MessageResponse> allAccess() {
		return ResponseEntity.ok(new MessageResponse("Public Content"));
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public ResponseEntity<MessageResponse> moderatorAccess() {
		return ResponseEntity.ok(new MessageResponse("Moderator Content"));
	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> userAccess() {
		return ResponseEntity.ok(new MessageResponse("User Content"));
	}
}