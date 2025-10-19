package com.storecapv1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storecapv1.dto.UserDto;
import com.storecapv1.entity.Users;
import com.storecapv1.exceptions.DuplicateRegisterEmailException;
import com.storecapv1.exceptions.DuplicateRegisterMobileException;
import com.storecapv1.repository.IUserRepository;

@Service
public class UserService {

	@Autowired
	private IUserRepository userRepository;


	public List<Users> registerUser(List<UserDto> userDto) {

		List<Users> user = new ArrayList<>();
		
		for(UserDto dto : userDto) {
			if(userRepository.findByEmail(dto.getEmail())!=null) {
				throw new DuplicateRegisterEmailException("Email already registered : " + dto.getEmail());
			}
			if(userRepository.findByMobile(dto.getMobile())!=null) {
				throw new DuplicateRegisterMobileException("Mobile number already registered : " + dto.getMobile());
			}
			
			Users users = new Users();
			String uniqueId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 5).toUpperCase();
			BeanUtils.copyProperties(dto, users);
			users.setUniqueId(uniqueId);
			
			user.add(users);
		}
		
		userRepository.saveAll(user);
		return user;
	}
}
