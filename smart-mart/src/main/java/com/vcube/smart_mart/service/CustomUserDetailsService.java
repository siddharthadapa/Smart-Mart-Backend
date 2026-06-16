package com.vcube.smart_mart.service;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vcube.smart_mart.entity.User;
import com.vcube.smart_mart.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

		String rawRole = user.getRole();
		String cleanRole = "ROLE_USER"; // Strict default fallback

		if (rawRole != null) {
			rawRole = rawRole.trim().toUpperCase();

			// Enforce explicit standard structural assignment mappings
			if (rawRole.equals("ROLE_ADMIN") || rawRole.equals("ADMIN")) {
				cleanRole = "ROLE_ADMIN";
			} else if (rawRole.equals("ROLE_USER") || rawRole.equals("USER")) {
				cleanRole = "ROLE_USER";
			}
		}

		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(cleanRole);

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				List.of(authority));
	}
}