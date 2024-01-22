package com.example.httpHeader.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.httpHeader.model.UserDto;

import jakarta.annotation.PostConstruct;

@Service
public class UserRepository {

	private final List<UserDto> userList = new ArrayList<>();

	@PostConstruct
	public void start(){
		userList.add(new UserDto(UUID.randomUUID().toString(), "홍길동", "1234"));
		userList.add(new UserDto(UUID.randomUUID().toString(), "유관순", "1234"));
		userList.add(new UserDto(UUID.randomUUID().toString(), "철수", "1234"));
	}

	public Optional<UserDto> findById(String id){
		return userList
			.stream()
			.filter(it -> it.getId().equals(id))
			.findFirst();
	}

	public Optional<UserDto> findByName(String name){
		return userList
			.stream()
			.filter(it -> it.getName().equals(name))
			.findFirst();
	}
}
