package com.panel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@GetMapping("/home")
	public String home() {
		System.out.println("user home");
		return "user home";
	}
	@GetMapping("/profile")
	public ResponseEntity<User> getUserProfileHandler(@RequestHeader("Authorization") String token) throws UserException {
		System.out.println("user profile");
		System.out.println("token : " + token);
		User findUserProfile = userService.findUserProfile(token);
		return new ResponseEntity<>(findUserProfile, HttpStatus.ACCEPTED);
	}
	
//	@GetMapping("/{query}")
//	public ResponseEntity<List<User>> searchUserhandler(@PathVariable("query") String q ){
//		List<User> searchUser = userService.searchUser(q);
//		return new ResponseEntity<List<User>>(searchUser , HttpStatus.OK);
//	}
	
	@PutMapping("/update")
	public ResponseEntity<ApiResponse> updatedUser(@RequestBody UserRequest req, @RequestHeader("Authorization") String token) throws UserException{
		 System.out.println("user update");
		User findUserProfile = userService.findUserProfile(token);
		userService.updateUser(findUserProfile.getId(), req);
		ApiResponse apiRes = new ApiResponse("user updates successFully", true);
		return new ResponseEntity<ApiResponse>(apiRes, HttpStatus.ACCEPTED);
	}
	
	
	
}
