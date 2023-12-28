package com.panel.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.panel.entity.User;
import com.panel.repository.UserRepository;

@Service
public class CustomUserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User findByEmail = userRepo.findByEmail(username);
		if(findByEmail == null) {
			throw new UsernameNotFoundException("user not found exception: " + username);
		}
		List<GrantedAuthority > autorities = new ArrayList<>();
		return new org.springframework.security.core.userdetails.User(findByEmail.getEmail(), findByEmail.getPassword(), autorities );
	}

}
