package com.masai.Services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Repositories.UserRepo;
import com.masai.Services.UserService;
import com.masai.entities.User;
import com.masai.payloads.UserDTO;
import com.masai.Exceptions.*;

@Service
public class UserServiceImpl implements UserService{
     
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDTO createUser(UserDTO userDto) {
		User user=this.dtoToUser(userDto);
		User savedUser=this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userDto, Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with "+userId+" not found!"));
//		UserDTO userDto1=this.userToDto(user);
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		User updatedUser= this.userRepo.save(user);
		UserDTO userDto1=userToDto(updatedUser);
		return userDto1;
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		return this.userToDto(this.userRepo.findById(userId).
				orElseThrow(()->new ResourceNotFoundException("User with "+userId+" not found!")));
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> userList=this.userRepo.findAll();
		List<UserDTO> userDtoList=userList.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
	    return userDtoList;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with "+userId+" not found!"));
        	this.userRepo.delete(user);	
	}

	private User dtoToUser(UserDTO userDto) {
		User user=this.modelMapper.map(userDto, User.class);
//		User user=new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;
	}
	
	private UserDTO userToDto(User user) {
		UserDTO userDto=this.modelMapper.map(user, UserDTO.class);
//		UserDTO userDto=new UserDTO();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}
}
