package com.challenge;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.challenge.domain.User;
import com.challenge.service.UserService;

@SpringBootApplication
public class ChallengeBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeBackEndApplication.class, args);
	}
	
	//Retorna un nuevo codificador de contraseñas
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// Srevicio para guardar el rol, guarda usuario para poder usar el servicio de usuario
	// Se la pasa el Id el cual se generara sobre la marcha, para no tener que pasar una identificiación
	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new com.challenge.domain.Role(null,"ROLE_USER"));
			userService.saveRole(new com.challenge.domain.Role(null, "ROLE_MANAGER"));
			userService.saveRole(new com.challenge.domain.Role(null, "ROLE_ADMIN"));
			userService.saveRole(new com.challenge.domain.Role(null, "ROLE_SUPER_ADMIN"));
			
			userService.saveUser(new User(null, "Carlos Oviedo", "caor", "123456", new ArrayList<>()));
			userService.saveUser(new User(null, "Yesenia Reyes", "yrc", "123456", new ArrayList<>()));
			userService.saveUser(new User(null, "Michelle Oviedo", "maor", "123456", new ArrayList<>()));
			userService.saveUser(new User(null, "Danna Oviedo", "dgor", "123456", new ArrayList<>()));
			
			userService.addRoleToUser("yrc", "ROLE_USER");
			userService.addRoleToUser("maor", "ROLE_MANAGER");
			userService.addRoleToUser("dgor", "ROLE_ADMIN");
			userService.addRoleToUser("caor", "ROLE_ADMIN");
			userService.addRoleToUser("caor", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("caor", "ROLE_USER");
		};
	}
	
	

}
