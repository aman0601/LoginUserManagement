package com.login.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}
