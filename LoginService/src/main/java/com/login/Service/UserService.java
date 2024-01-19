package com.login.Service;

import com.login.Model.User;

public interface UserService {
	User findByUsername(String username);
}
