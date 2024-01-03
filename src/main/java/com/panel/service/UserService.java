package com.panel.service;

import java.util.List;

import com.panel.entity.User;
import com.panel.exceptionHandler.UserException;
import com.panel.request.UserRequest;

public interface UserService {
	List<User> getAllUser();

	public User getUserById(int id) throws UserException;

    public User updateUser(int id, UserRequest request) throws UserException;

    public User findUserProfile(String jwt) throws UserException;
    
//    public List<User> searchUser(String query);
    public List<User> searchUsers(String keyword);
    
    public void deleteUser(int id);
}
