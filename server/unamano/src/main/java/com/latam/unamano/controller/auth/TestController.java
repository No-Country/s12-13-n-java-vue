package com.latam.unamano.controller.auth;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/test")
public class TestController {
	@PostMapping
	public String login() {
		
		return "Si sirve";
		
	}

}
