package com.webprogramming.security;

import com.webprogramming.entity.User;
import com.webprogramming.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		User user = userRepository.findByUsernameOrEmail(login, login)
				.orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy username/email: " + login));

		return new CustomUserDetails(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(),
				user.getFullName(), user.getImages(), user.getRole().getName(), user.isEnabled());
	}
}
