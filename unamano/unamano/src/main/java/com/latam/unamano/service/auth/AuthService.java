package com.latam.unamano.service.auth;

import java.util.Optional;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.latam.unamano.dto.auth.AuthResponseDto;
import com.latam.unamano.dto.auth.LoginRequestDto;
import com.latam.unamano.dto.auth.LoginResponseDto;
import com.latam.unamano.dto.exceptions.UsernameOrPasswordIncorretException;
import com.latam.unamano.exceptions.IncorretRoleException;
import com.latam.unamano.persistence.entities.login.Login;
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
		try {
	        authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(loginRequestDto.username(), loginRequestDto.password())
	        );
	    } catch (AuthenticationException e) {
	        throw new UsernameOrPasswordIncorretException("Usuario o contraseña incorrecto");
	    }
		UserDetails user = loginRepository.findByUsername(loginRequestDto.username()).orElseThrow();
		
		if (!user.getAuthorities().stream()
		        .anyMatch(authority -> authority.getAuthority().equals(loginRequestDto.role().toString()))) {
		    throw new IncorretRoleException("Rol incorrecto para este usuario");
		}
		
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
