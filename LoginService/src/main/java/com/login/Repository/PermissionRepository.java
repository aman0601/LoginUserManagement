package com.login.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.Model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
	Optional<Permission> findByName(String name);
}
