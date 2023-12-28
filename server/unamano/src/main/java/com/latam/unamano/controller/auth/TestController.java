package com.latam.unamano.controller.auth;


import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/test")

public class TestController {
	@GetMapping("ClientAuth")
	@Secured("ROLE_CLIENT")
	public String clientAuth() {
		
		return "Soy un cliente";
		
	}

	@GetMapping("WorkerAuth")
	@Secured("ROLE_WORKER")
	public String WorkerAuth() {

		return "Soy un Trabajador";

	}

}
