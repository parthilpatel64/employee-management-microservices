package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;
import com.example.demo.requestDto.LoginRequestDto;
import com.example.demo.responseDto.AuthResponseDto;
import com.example.demo.utils.JWTUtil;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JWTUtil jwtUtil;

	public AuthResponseDto login(LoginRequestDto request) {

		Users user = userRepository.findByUsername(request.getUserName())
				.orElseThrow(() -> new RuntimeException("User not found"));

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid credentials");
		}

		String token = jwtUtil.generateToken(user);

		return new AuthResponseDto(token, user.getUsername(), user.getRoles().stream().map(Role::getName).toList());
	}
}
