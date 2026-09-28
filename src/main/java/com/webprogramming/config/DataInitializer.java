package com.webprogramming.config;

import com.webprogramming.entity.Role;
import com.webprogramming.entity.User;
import com.webprogramming.repository.RoleRepository;
import com.webprogramming.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

	@Bean
	CommandLineRunner init(RoleRepository roleRepository, UserRepository userRepository,
			PasswordEncoder passwordEncoder, @Value("${DEMO_USERNAME:user01}") String username,
			@Value("${DEMO_EMAIL:user01@gmail.com}") String email, @Value("${DEMO_PASSWORD:123456}") String password,
			@Value("${DEMO_FULL_NAME:Trần Thị Phương Trang}") String fullName,
			@Value("${DEMO_IMAGES:/images/avatar-default.svg}") String images) {
		return args -> {
			Role userRole = roleRepository.findByName("ROLE_USER")
					.orElseGet(() -> roleRepository.save(Role.builder().name("ROLE_USER").build()));

			roleRepository.findByName("ROLE_ADMIN")
					.orElseGet(() -> roleRepository.save(Role.builder().name("ROLE_ADMIN").build()));

			if (userRepository.findByUsername(username).isEmpty() && userRepository.findByEmail(email).isEmpty()) {
				userRepository
						.save(User.builder().username(username).email(email).password(passwordEncoder.encode(password))
								.fullName(fullName).images(images).role(userRole).enabled(true).build());
			}
		};
	}
}
