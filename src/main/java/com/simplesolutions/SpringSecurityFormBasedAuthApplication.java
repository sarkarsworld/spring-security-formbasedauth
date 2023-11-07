package com.simplesolutions;

import com.simplesolutions.models.User;
import com.simplesolutions.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringSecurityFormBasedAuthApplication implements CommandLineRunner {
	@Autowired
	UserRepo userRepo;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityFormBasedAuthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		User userAdmin = new User("Admin", this.bCryptPasswordEncoder.encode("Admin"), "admin@gmail.com", "ROLE_ADMIN");
		userRepo.save(userAdmin);

		User userNormal = new User("Normal", this.bCryptPasswordEncoder.encode("Normal"), "normal@gmail.com", "ROLE_NORMAL");
		userRepo.save(userNormal);

	}
}
