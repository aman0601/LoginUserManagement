package com.login.Model.Response;

import java.util.List;

public class JwtResponse {
	private String email;
	private Long id;
	private List<String> roles;
	private String token;
	private String type = "Bearer";
	private String username;

	public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
	}

	public String getEmail() {
		return email;
	}

	public Long getId() {
		return id;
	}

	public List<String> getRoles() {
		return roles;
	}

	public String getTokenType() {
		return type;
	}

	public String getUsername() {
		return username;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}