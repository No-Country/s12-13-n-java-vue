package com.latam.unamano.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
<<<<<<< HEAD
=======
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
>>>>>>> dev-backend
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.latam.unamano.infra.jwt.JwtAuthenticationFilter;


import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
<<<<<<< HEAD
=======
@EnableMethodSecurity(securedEnabled = true)
>>>>>>> dev-backend
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final AuthenticationProvider authProvider;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http		
<<<<<<< HEAD
			.csrf(csrf -> 
=======
			.csrf(csrf ->
>>>>>>> dev-backend
					csrf
					.disable())
			.authorizeHttpRequests(authRequest -> 
			authRequest
<<<<<<< HEAD
			.requestMatchers("api/auth/**").permitAll()
=======
				.requestMatchers("api/auth/**", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
>>>>>>> dev-backend
			.anyRequest().authenticated()
			)
			.sessionManagement(sessionManager -> sessionManager
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authenticationProvider(authProvider)
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
			.build();
	}
	
	

}
