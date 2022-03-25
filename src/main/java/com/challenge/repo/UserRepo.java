package com.challenge.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
	
	User findByUsername(String username);

}
