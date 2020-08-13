package com.szte.recommender.project.service;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.szte.recommender.project.entity.User;

public class UserDetailsImpl implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7335584989160350044L;

	private static User user;
	
	public UserDetailsImpl(User user) {
		UserDetailsImpl.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		String[] roles = user.getRole().split(",");
		for(String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder(); 
        return encoder.encode(user.getPassword());
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}
	
	public static int getUserId() {
		return user.getId();
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

	@Override
	public boolean isEnabled() {
		return true;
	}

}
