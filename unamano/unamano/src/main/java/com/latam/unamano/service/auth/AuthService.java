package com.latam.unamano.service.auth;

import java.util.Optional;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.latam.unamano.dto.auth.AuthResponseDto;
import com.latam.unamano.dto.auth.LoginRequestDto;
import com.latam.unamano.dto.auth.LoginResponseDto;
import com.latam.unamano.persistence.entities.Login;
import com.latam.unamano.persistence.repositories.login.LoginRepository;
import com.latam.unamano.service.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
		
	private final LoginRepository loginRepository;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	public AuthResponseDto login(LoginRequestDto loginRequestDto) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.username(), loginRequestDto.password()));
		UserDetails user = loginRepository.findByUsername(loginRequestDto.username()).orElseThrow();
		String token = jwtService.getToken(user);
		return new AuthResponseDto(token);
	}

	public LoginResponseDto getUserDetails() {
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<UserDetails> userOptional = loginRepository.findByUsername(username);
		if (!userOptional.isPresent()) {

			throw new UsernameNotFoundException("user not found");
		}
		Login user = (Login) userOptional.get();
		return new LoginResponseDto(user);
	}

}
