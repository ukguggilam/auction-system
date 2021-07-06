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
		return userRepository.findById(Long.valueOf(id)).get();
	}

	@Override
	public UserEntity addNewUser(UserEntity user) throws Exception {
		return userRepository.save(user);
	}

	@Override
	public UserEntity updateUser(String id, UserEntity user) throws Exception {
		if(userRepository.existsById(Long.valueOf(id)))
			return userRepository.save(user);
		throw new RuntimeException("No such user exists with user id -> "+id);
	}

	@Override
	public UserEntity deleteUser(String id) throws Exception {
		if(userRepository.existsById(Long.valueOf(id))) {
			UserEntity u = userRepository.findById(Long.valueOf(id)).get();
			userRepository.delete(u);
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
