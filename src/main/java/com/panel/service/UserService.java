package com.panel.service;

import java.util.List;

import com.panel.entity.User;
import com.panel.exceptionHandler.UserException;
import com.panel.request.UserRequest;

public interface UserService {
//	List<User> getAllUser(User user);

	public User getUserById(int id) throws UserException;

//    User createUser(User user);

    public User updateUser(int id, UserRequest request) throws UserException;

//    void deleteUser(int id);
    
    public User findUserProfile(String jwt) throws UserException;
    
    public List<User> searchUser(String query);
}
