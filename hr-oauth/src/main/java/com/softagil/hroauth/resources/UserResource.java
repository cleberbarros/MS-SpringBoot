package com.softagil.hroauth.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softagil.hroauth.entities.User;
import com.softagil.hroauth.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserResource {

	private final UserService service;
	
	@GetMapping(value = "/search")
	public ResponseEntity<User> findByEmail(@RequestParam String email){
		try {

			User user = service.finbyEmail(email);
		return ResponseEntity.ok(user);

		}catch(IllegalArgumentException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
}
