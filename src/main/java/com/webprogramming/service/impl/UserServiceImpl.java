package com.webprogramming.service.impl;

import com.webprogramming.dto.UserDTO;
import com.webprogramming.entity.User;
import com.webprogramming.mapper.UserMapper;
import com.webprogramming.repository.UserRepository;
import com.webprogramming.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	@Override
	public UserDTO findById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Không tìm thấy user: " + id));

		return userMapper.toDTO(user);
	}
}
