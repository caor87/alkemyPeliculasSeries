package com.challenge.service;

import java.util.List;

import com.challenge.domain.Role;
import com.challenge.domain.User;

public interface UserService {
	
	User saveUser(User user);
	Role saveRole(Role role);
	//metodo que impide que se repita un userName
	void addRoleToUser(String username, String roleName);
	User getUser(String username);
	List<User> getUsers();

}
