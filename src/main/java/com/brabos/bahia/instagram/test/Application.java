package com.brabos.bahia.instagram.test;

import com.brabos.bahia.instagram.test.domains.UserProfile;
import com.brabos.bahia.instagram.test.domains.enums.Profile;
import com.brabos.bahia.instagram.test.repositories.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private UserProfileRepository repository;

	@Autowired
	private BCryptPasswordEncoder pe;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(repository.findByEmail("admin@gmail.com") == null) {
			UserProfile user = new UserProfile(null, "admin@gmail.com", "admin", pe.encode("12345678"), null);
			user.addProfiles(Profile.ADMIN);
			repository.save(user);
		}
	}
}
