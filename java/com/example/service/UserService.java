package com.example.service;
import java.util.List;

import com.example.payload.UserDto;

public interface UserService {

	public UserDto saveUser(UserDto user);
   public UserDto updateUser(UserDto user , Integer userId);
   public UserDto userById(Integer userId);
   public List<UserDto> userList();
   public void deleteUser(Integer userId);
}
