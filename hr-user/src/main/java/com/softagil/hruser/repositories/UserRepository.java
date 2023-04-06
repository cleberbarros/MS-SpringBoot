package com.softagil.hruser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softagil.hruser.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByEmail(String email);
}
