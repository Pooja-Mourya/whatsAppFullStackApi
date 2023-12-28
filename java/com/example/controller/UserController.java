package com.example.controller;

import com.example.payload.UserDto;
import com.example.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user){
    	UserDto saveUser = userService.saveUser(user);
    	return new ResponseEntity<UserDto>(saveUser, HttpStatus.CREATED);
    }
    
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto user , @PathVariable("userId") Integer userId){
    	UserDto updateUser = userService.updateUser(user, userId);
    	return new ResponseEntity<UserDto>(updateUser, HttpStatus.ACCEPTED);
    }
    
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer userId){
    	userService.deleteUser(userId);
    	return new ResponseEntity<>(Map.of("message", "user deleted successfully"), HttpStatus.OK);
    }
   
    @GetMapping
    public ResponseEntity<List<UserDto>> Alluser(){
    	List<UserDto> userList = userService.userList();
    	return new ResponseEntity<List<UserDto>>( userList, HttpStatus.ACCEPTED);
    	
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> userById(@PathVariable("userId") Integer userId){
    	UserDto userById = userService.userById(userId);
    	return new ResponseEntity<UserDto>(userById, HttpStatus.OK);
    }
}
