package com.masai.Services;

import java.util.List;

import com.masai.payloads.UserDTO;

public interface UserService {
	
UserDTO createUser(UserDTO userDto);

UserDTO updateUser(UserDTO userDto,Integer userId);

UserDTO getUserById(Integer userId);

List<UserDTO> getAllUsers();

void deleteUser(Integer userId);

}
