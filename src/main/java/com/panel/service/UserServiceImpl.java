package com.panel.service;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.panel.config.TokenProvider;
import com.panel.entity.User;
import com.panel.exceptionHandler.ResourceNotFoundException;
import com.panel.exceptionHandler.UserException;
import com.panel.repository.UserRepository;
import com.panel.request.UserRequest;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;


	TokenProvider tokenProvider = new TokenProvider();

	@Override
	public User getUserById(int id) throws UserException {
		// TODO Auto-generated method stub
		Optional<User> opt = userRepo.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new UserException("user not found" + id);

	}

	@Override
	public User updateUser(int id, UserRequest request) throws UserException {
		// TODO Auto-generated method stub
		User user = getUserById(id);
		if (request.getDisplayName() != null) {
			user.setDisplayName(request.getDisplayName());
		}
		if (request.getProfilePicture() != null) {
			user.setProfilePicture(request.getProfilePicture());
		}

		return userRepo.save(user);
	}

	@Override
	public User findUserProfile(String jwt) throws UserException {
		// TODO Auto-generated method stub
		
		System.out.println("user jwt : " + jwt);
		String email = tokenProvider.getEmailFromToken(jwt);
		System.out.println("email : " + email);
		if (email == null) {
			throw new BadCredentialsException("invailed token");
		}
		User user = userRepo.findByEmail(email);
		System.out.println("user : " + user);
		if (user == null) {
			throw new UserException("user not found exception");
		}
		return user;
	}

	@Override
    public List<User> searchUsers(String keyword) {
        return userRepo.findByUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword);
    }
	
	@Override
	public List<User> getAllUser( ) {
		List<User> findAll = this.userRepo.findAll();
		return findAll;
	}

	@Override
	public void deleteUser(int id) {
		this.userRepo.deleteById(id);;		
	}
	
}
