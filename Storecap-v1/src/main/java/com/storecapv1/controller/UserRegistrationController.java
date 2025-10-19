package com.storecapv1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.storecapv1.dto.UserDto;
import com.storecapv1.entity.Users;
import com.storecapv1.service.UserService;
import com.storecapv1.utils.APIResponse;

@Controller
@RequestMapping("/api/storecap/v1/users")
public class UserRegistrationController {

	@Autowired
	private UserService userService;
	
	
	@PostMapping("/registerUser")
	public ResponseEntity<APIResponse<List<Users>>> addUser(@RequestBody List<UserDto> userDto) {
		
		List<Users> user = userService.registerUser(userDto);
		
		APIResponse<List<Users>> response = new APIResponse<>();
		response.setMessage("User registered successfully");
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setData(user);
        
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
