package com.masai.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Services.UserService;
import com.masai.payloads.ApiResponse;
import com.masai.payloads.UserDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDto){
		UserDTO ud= userService.createUser(userDto);
		return new ResponseEntity<>(ud,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<UserDTO> updateUser(@RequestBody @Valid UserDTO userDto,@PathVariable Integer userId){
     		UserDTO updatedUser= this.userService.updateUser(userDto, userId);
	        return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId")Integer uid){
		this.userService.deleteUser(uid);
		return new ResponseEntity(new ApiResponse("User deleted succesfully!",true),HttpStatus.OK);
	}
	
	@GetMapping("/allUsers")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getSingleUser(@PathVariable Integer userId){
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
	
}
