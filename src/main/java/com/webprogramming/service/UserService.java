package com.webprogramming.service;

import com.webprogramming.dto.UserDTO;

public interface UserService {

	UserDTO findById(Long id);
}
