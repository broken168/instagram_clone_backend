package com.brabos.bahia.instagram.test;

import com.brabos.bahia.instagram.test.domains.Post;
import com.brabos.bahia.instagram.test.domains.UserProfile;
import com.brabos.bahia.instagram.test.repositories.PostRepository;
import com.brabos.bahia.instagram.test.repositories.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserProfileRepository userProfileRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		UserProfile up1 = new UserProfile(null, "gabriel@gmail.com", "gabriel", "kkk", null, 0L, 0L, 0L);
		UserProfile up2 = new UserProfile(null, "india@gmail.com", "india", "kkk", null, 0L, 0L, 0L);

		Post p1 = new Post(null, "descrição kkk", null, up1);
		Post p2 = new Post(null, "descrição 2", null, up1);
		Post p3 = new Post(null, "descrição klkk3333", null, up2);

		up1.getPosts().addAll(Arrays.asList(p1, p2));
		up2.getPosts().add(p3);

		userProfileRepository.saveAll(Arrays.asList(up1, up2));
		postRepository.saveAll(Arrays.asList(p1, p2, p3));


	}
}
