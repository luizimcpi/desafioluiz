package com.company.desafioluiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.desafioluiz.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	User findById(Long id);
	User findByToken(String token);
	User findByEmailAndPassword(String email, String password);
}
