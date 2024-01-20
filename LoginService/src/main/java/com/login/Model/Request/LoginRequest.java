package com.login.Model.Request;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
	@NotBlank
	private String password;

	@NotBlank
	private String username;

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}