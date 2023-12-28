package com.panel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.panel.config.TokenProvider;
import com.panel.entity.User;
import com.panel.exceptionHandler.UserException;
import com.panel.payload.AuthResponse;
import com.panel.repository.UserRepository;
import com.panel.request.LoginRequest;
import com.panel.service.CustomUserService;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	TokenProvider jwtToken = new TokenProvider();
	@Autowired
	private CustomUserService customuser;

	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> userSignup(@RequestBody User user) throws UserException {
//		System.out.println("call function");
		String email = user.getEmail();
		String displayName = user.getDisplayName();
		String password = user.getPassword();
		String username = user.getUsername()
;		User isUser = userRepo.findByEmail(email);

		if (isUser != null) {
			throw new UserException("email is used with another account" + email);
		}
		User createUser = new User();
		createUser.setDisplayName(displayName);
		createUser.setEmail(email);
		createUser.setPassword(passwordEncoder.encode(password));
		createUser.setUsername(username);
		userRepo.save(createUser);
																																																	
		Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String generatedToken = jwtToken.generateToken(authentication);
		AuthResponse authRes = new AuthResponse(generatedToken, true);

		return new ResponseEntity<>(authRes, HttpStatus.CREATED);

	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> userLogin(@RequestBody LoginRequest req) {
		String email = req.getEmail();
		String password = req.getPassword();
		Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String generatToken = jwtToken.generateToken(authentication);
		AuthResponse authRes = new AuthResponse(generatToken, true);

		return new ResponseEntity<AuthResponse>(authRes, HttpStatus.CREATED);
	}

	public Authentication authanticated(String email, String password) {
		UserDetails userDetails = customuser.loadUserByUsername(password);
		if (userDetails == null) {
			throw new BadCredentialsException("invalid password and username");
		}
		if (passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("invalid password and username");
		}

		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

	}

}
