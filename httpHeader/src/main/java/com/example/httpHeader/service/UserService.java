package com.example.httpHeader.service;

import org.springframework.stereotype.Service;

import com.example.httpHeader.db.UserRepository;
import com.example.httpHeader.model.LoginRequest;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	// login logic
	public String login(
		LoginRequest loginRequest,
		HttpServletResponse response
	){
		var id = loginRequest.getId();
		var pw = loginRequest.getPassword();

		var optionalUser = userRepository.findByName(id);
		if(optionalUser.isPresent()){
			var userDto = optionalUser.get();
			if(userDto.getPassword().equals(pw)){
				return userDto.getId();
			}
		}else{
			throw new RuntimeException("User Not Found");
		}

		return null;
	}
}
