package com.auction.controllers;

import com.auction.entity.UserEntity;
import com.auction.model.Response;
import com.auction.model.ResponseTypeEnum;
import com.auction.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/{userId}", method = RequestMethod.GET)
	public Response<?> getUserById(@PathVariable("userId")String userId) {
		try {
			return new Response<UserEntity>(ResponseTypeEnum.SUCCESS,userService.getById(userId));
		}
		catch(Exception e) {
			return new Response<String>(ResponseTypeEnum.ERROR,e.getMessage());
		}
	}
//
//	@RequestMapping(value="/email", method = RequestMethod.GET)
//	public Response<?> getUserByEmailId(@PathVariable("email")String email) {
//		try {
//			return new Response<User>(ResponseTypeEnum.SUCCESS,userService.getByEmail(email));
//		}
//		catch(Exception e) {
//			return new Response<String>(ResponseTypeEnum.ERROR,e.getMessage());
//		}
//	}

	@RequestMapping(value="/", method = RequestMethod.POST)
	public Response<?> addNewUser(@RequestBody UserEntity user) {
		try {
			return new Response<UserEntity>(ResponseTypeEnum.SUCCESS,userService.addNewUser(user));
		}
		catch(Exception e) {
			return new Response<String>(ResponseTypeEnum.ERROR,e.getMessage());
		}
	}

	@RequestMapping(value="/{userId}", method = RequestMethod.PUT)
	public Response<?> updateUser(@PathVariable("userId")String userId,@RequestBody UserEntity user) {
		try {
			return new Response<UserEntity>(ResponseTypeEnum.SUCCESS,userService.updateUser(userId, user));
		}
		catch(Exception e) {
			return new Response<String>(ResponseTypeEnum.ERROR,e.getMessage());
		}
	}

	@RequestMapping(value="/{userId}", method = RequestMethod.DELETE)
	public Response<?> deleteUserById(@PathVariable("userId")String userId) {
		try {
			return new Response<UserEntity>(ResponseTypeEnum.SUCCESS,userService.deleteUser(userId));
		}
		catch(Exception e) {
			return new Response<String>(ResponseTypeEnum.ERROR,e.getMessage());
		}
	}

}
