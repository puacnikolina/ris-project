
package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.UserRepository;

import model.User;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	UserRepository ur;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User u = ur.findByUsername(username);
		if (u == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		UserDetails ud = new CustomUserDetail(u);
		return ud;
	}
}
