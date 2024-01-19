package com.login.Service;

import com.login.Model.Permission;

public interface PermissionService {
	Permission findByName(String name);
}
