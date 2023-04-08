package com.softagil.hroauth.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.softagil.hroauth.entities.User;
import com.softagil.hroauth.feignclients.UserFeignClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{

	private final UserFeignClient userFeignClient;
	
	//ESSE MÉTODO CONTINUA SOBRE POR SER PROJETO DIDATICO 
	public User finbyEmail(String email) {
		User user = userFeignClient.findByEmail(email).getBody();
		if(user == null) {
			log.error("E-mail not found: "+email);
			throw new IllegalArgumentException("Email not found");
		}
		log.info("E-mail found: "+email);
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
 
		User user = userFeignClient.findByEmail(username).getBody();
		if(user == null) {
			log.error("E-mail not found: "+username);
			throw new UsernameNotFoundException("Email not found");
		}
		log.info("E-mail found: "+username);
		return user;

	}
	
}
