package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.requestDto.LoginRequestDto;
import com.example.demo.responseDto.AuthResponseDto;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	  @Autowired
	    private AuthService authService;

	    @PostMapping("/login")
	    public ResponseEntity<AuthResponseDto> login(
	            @RequestBody LoginRequestDto request) {
	        return ResponseEntity.ok(authService.login(request));
	    }

}
