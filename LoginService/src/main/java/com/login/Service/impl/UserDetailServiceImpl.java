package com.login.Service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.login.Model.Role;
import com.login.Model.User;
import com.login.Service.UserDetailService;
import com.login.Service.UserService;

//UserDetailsServiceImpl.java
@Service
public class UserDetailServiceImpl implements UserDetailService {

	private final UserService userService;

	@Autowired
	public UserDetailServiceImpl(UserService userService) {
		this.userService = userService;
	}

	private Set<GrantedAuthority> getAuthorities(Set<Role> roles) {
		return roles.stream().flatMap(role -> role.getPermissions().stream())
				.map(permission -> new SimpleGrantedAuthority(permission.getName())).collect(Collectors.toSet());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

		Set<GrantedAuthority> authorities = user.getRoles().stream().flatMap(role -> role.getPermissions().stream())
				.map(permission -> new SimpleGrantedAuthority(permission.getName())).collect(Collectors.toSet());

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				authorities);
	}
}
