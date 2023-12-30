package com.panel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.panel.entity.User;
import com.panel.exceptionHandler.UserException;
import com.panel.payload.ApiResponse;
import com.panel.request.UserRequest;
import com.panel.service.UserServiceImpl;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@GetMapping("/profile")
	public ResponseEntity<User> getUserProfileHandler(@RequestHeader("Authorization") String token)
			throws UserException {
		System.out.println("user profile");
		System.out.println("token : " + token);
		User findUserProfile = userService.findUserProfile(token);
		return new ResponseEntity<User>(findUserProfile, HttpStatus.ACCEPTED);
	}

	@GetMapping("/search")
	public ResponseEntity<List<User>> searchUsers(@RequestParam String keyword) {
		List<User> searchResults = userService.searchUsers(keyword);
		return new ResponseEntity<>(searchResults, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<ApiResponse> updatedUser(@RequestBody UserRequest req,
			@RequestHeader("Authorization") String token) throws UserException {
//		 System.out.println("user update");
		User findUserProfile = userService.findUserProfile(token);
		userService.updateUser(findUserProfile.getId(), req);
		ApiResponse apiRes = new ApiResponse("user updates successFully", true);
		return new ResponseEntity<ApiResponse>(apiRes, HttpStatus.ACCEPTED);
	}

	@GetMapping("/allUser")
	public ResponseEntity<List<User>> allUser() {
		List<User> allUser = userService.getAllUser();
		return new ResponseEntity<List<User>>(allUser, HttpStatus.ACCEPTED);
	}
}
