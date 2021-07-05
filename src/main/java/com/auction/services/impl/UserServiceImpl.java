package com.auction.services.impl;

import com.auction.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auction.repository.UserRepository;
import com.auction.services.UserService;

@Service
public class UserServiceImpl  implements  UserService{

	@Autowired
	UserRepository userRepository;

	@Override
	public UserEntity getById(String id) throws Exception {
		return userRepository.findOne(Long.valueOf(id));
	}

	@Override
	public UserEntity addNewUser(UserEntity user) throws Exception {
		return userRepository.save(user);
	}

	@Override
	public UserEntity updateUser(String id, UserEntity user) throws Exception {
		if(userRepository.exists(Long.valueOf(id)))
			return userRepository.save(user);
		throw new RuntimeException("No such user exists with user id -> "+id);
	}

	@Override
	public UserEntity deleteUser(String id) throws Exception {
		if(userRepository.exists(Long.valueOf(id))) {
			UserEntity u = userRepository.findOne(Long.valueOf(id));
			userRepository.delete(Long.valueOf(id));
			return u;
		}
		throw new RuntimeException("No such user exists with user id -> "+id);
	}

//	@Override
//	public User getByEmail(String email) throws Exception {
//		System.out.println("email -> "+email);
//		return userRepository.findByEmail(email);
//	}

}
