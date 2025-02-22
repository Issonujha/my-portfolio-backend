package com.example.myportfolio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.myportfolio.dto.Signup;
import com.example.myportfolio.entity.User;

@Mapper
public interface UserMapper {
	
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	Signup toSignUp(User user);

}
