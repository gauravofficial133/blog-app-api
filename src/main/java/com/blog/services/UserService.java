package com.blog.services;

import java.util.List;

import com.blog.entities.User;
import com.blog.payloads.UserDto;

public interface UserService {
	UserDto registerNewUser(UserDto user);
	
	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user,int userId);
	
	UserDto getUserById(int userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(int userId);
}
