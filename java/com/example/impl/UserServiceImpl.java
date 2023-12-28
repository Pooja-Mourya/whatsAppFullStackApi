package com.example.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.exceptionHandler.ResouseNotFoundException;
import com.example.payload.UserDto;
import com.example.repository.UserRepository;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ModelMapper modelMapper;
	@Override
	public UserDto saveUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		Date date = Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
		user.setCreateAt(date);
		User save = this.userRepo.save(user);
		
		return this.userToDto(save);
	}

	@Override
	public UserDto updateUser(UserDto user, Integer userId) {
		userRepo.findById(userId)
				.orElseThrow(() -> new ResouseNotFoundException( "User", "id", null));
		user.setName(user.getName());
		user.setEmail(user.getEmail());
		user.setPassword(user.getPassword());
		user.setAbout(user.getAbout());
		Date date = Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
		user.setCreateAt(date);
		User dtoToUser = this.dtoToUser(user);
		User save = userRepo.save(dtoToUser);
		return this.userToDto(save);
	}

	@Override
	public UserDto userById(Integer userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResouseNotFoundException("user not found : " + userId, null, null));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> userList() {
		List<User> allUser = userRepo.findAll();
		List<UserDto> findUserDto = allUser.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return findUserDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(()-> new ResouseNotFoundException("user not found : "  , " id ", null));
		userRepo.delete(user);
	}

	private User dtoToUser(UserDto userDto) {
		User u = modelMapper.map(userDto, User.class);
//		u.setId(userDto.getId());
//		u.setName(userDto.getName());
//		u.setEmail(userDto.getEmail());
//		u.setPassword(userDto.getPassword());
//		u.setAbout(userDto.getAbout());
//		u.setCreateAt(userDto.getCreateAt());
		return u;
	}

	public UserDto userToDto(User d) {
		UserDto ud = modelMapper.map(d, UserDto.class);
//		ud.setId(d.getId());
//		ud.setName(d.getName());
//		ud.setEmail(d.getEmail());
//		ud.setPassword(d.getPassword());
//		ud.setAbout(d.getAbout());
//		ud.setCreateAt(d.getCreateAt());
		return ud;
	}

}
