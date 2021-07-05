package com.auction.services;

import com.auction.entity.UserEntity;

public interface UserService {
	
	UserEntity getById(String id)throws Exception;

//	User getByEmail(String email)throws Exception;

	UserEntity addNewUser(UserEntity user)throws Exception;

	UserEntity updateUser(String id, UserEntity user)throws Exception;

	UserEntity deleteUser(String id)throws Exception;

}
