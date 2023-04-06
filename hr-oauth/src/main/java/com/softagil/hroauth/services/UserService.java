package com.softagil.hroauth.services;

import org.springframework.stereotype.Service;

import com.softagil.hroauth.entities.User;
import com.softagil.hroauth.feignclients.UserFeignClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserFeignClient userFeignClient;
	
	public User finbyEmail(String email) {
		User user = userFeignClient.findByEmail(email).getBody();
		if(user == null) {
			log.error("E-mail not found: "+email);
			throw new IllegalArgumentException("Email not found");
		}
		log.info("E-mail found: "+email);
		return user;
	}
	
}
