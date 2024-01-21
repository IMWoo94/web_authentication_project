package com.example.session.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.session.db.UserRepository;
import com.example.session.model.LoginRequest;
import com.example.session.model.UserDto;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public void login (
		LoginRequest loginRequest,
		HttpSession httpSession
	){
		var id = loginRequest.getId();
		var pw = loginRequest.getPassword();

		var optionalUser = userRepository.findByName(id);
		if(optionalUser.isPresent()){
			var userDto = optionalUser.get();
			if(userDto.getPassword().equals(pw)){
				// 세션에 정보 저장
				httpSession.setAttribute("USER", userDto);

			}else{
				throw new RuntimeException("Password Not Match");
			}
		}else{
			// 없는 유저
			throw new RuntimeException("User Not Found");
		}


	}
}
