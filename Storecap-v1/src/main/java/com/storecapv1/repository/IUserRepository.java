package com.storecapv1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storecapv1.entity.Users;

public interface IUserRepository extends JpaRepository<Users, Long> {

	Users findByUniqueIdContainingIgnoreCase(String userId);
	
	Users findByEmail(String email);

	Users findByMobile(String mobile);
}
