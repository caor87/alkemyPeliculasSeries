package com.challenge.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.domain.Role;

public interface RoleRepo extends JpaRepository<Role, Long>{
	Role findByName(String name);
}
