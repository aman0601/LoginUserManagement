package com.login.Model.Request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignupRequest {
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(min = 6, max = 40)
	private String password;

	private Set<String> role;

	@NotBlank
	@Size(min = 3, max = 20)
	private String username;

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Set<String> getRole() {
		return this.role;
	}

	public String getUsername() {
		return username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}