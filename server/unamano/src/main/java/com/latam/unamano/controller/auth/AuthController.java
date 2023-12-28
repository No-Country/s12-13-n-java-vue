package com.latam.unamano.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.latam.unamano.dto.auth.AuthResponseDto;
import com.latam.unamano.dto.auth.LoginRequestDto;
import com.latam.unamano.dto.auth.UserResponseDto;
import com.latam.unamano.service.auth.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	
	@PostMapping(value = "login")
	public ResponseEntity<AuthResponseDto> login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
		
		return ResponseEntity.ok(authService.login(loginRequestDto))
		;
	}
	
	@GetMapping(value= "details")
	public ResponseEntity<UserResponseDto> details(){
		return ResponseEntity.ok(authService.getUserDetails());
	}

}
