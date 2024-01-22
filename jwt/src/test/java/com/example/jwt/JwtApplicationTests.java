package com.example.jwt;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jwt.service.JwtService;

@SpringBootTest
class JwtApplicationTests {
	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {
	}

	@Test
	void tokenCreate(){
		var claims = new HashMap<String, Object>();
		claims.put("user_id", 923);

		// 현재시간 + 10분 유효
		var expireAt = LocalDateTime.now().plusMinutes(10);

		var jwtToken = jwtService.create(claims, expireAt);

		System.out.println(jwtToken);
	}

	@Test
	void tokenValidation(){

	}

}
