package com.example.demo.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import model.User;

public class CustomUserDetail implements UserDetails {

	private static final long serialVersionUID = 1L;
	private User u;

	public User getU() {
		return u;
	}
	public void setU(User u) {
		this.u = u;
	}
	public CustomUserDetail(User user) {
		this.u = user;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + u.getRole().getName().toUpperCase()));
		return authorities;
	}
	@Override
	public String getPassword() {
		return u.getPassword();
	}
	@Override
	public String getUsername() {
		return u.getUsername();
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
}