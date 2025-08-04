package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Role findByName(String name);
}
