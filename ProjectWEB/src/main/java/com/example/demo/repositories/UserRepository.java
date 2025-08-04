package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);

	boolean existsByUsername(String username);
}