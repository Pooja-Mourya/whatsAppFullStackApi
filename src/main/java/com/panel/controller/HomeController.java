package com.panel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.panel.entity.User;
import com.panel.payload.AppConstant;
import com.panel.service.UserServiceImpl;

@RestController
//@RequestMapping("/user")
public class HomeController {
	
	@GetMapping("/api/")
	public ResponseEntity<String>home(){
		return new ResponseEntity<>("hello api", HttpStatus.OK);
	}

//	@Autowired
//	private UserServiceImpl userService;
//	
//    @PostMapping("/register")
//    public ResponseEntity<User> createUser(@RequestBody User user) {
//        User createdUser = userService.createUser(user);
//        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
//    }
//    
//    @GetMapping("/getAll")
//    public ResponseEntity<List<User>> getAllUser(User user){
//    	List<User> userList = userService.getAllUser(user);
//    	return new ResponseEntity<>(userList, HttpStatus.FOUND );
//    }
//    @GetMapping("/getById/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable int id) {
//        User user = userService.getUserById(id);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
//        User updatedUser = userService.updateUser(id, user);
//        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
//        userService.deleteUser(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
