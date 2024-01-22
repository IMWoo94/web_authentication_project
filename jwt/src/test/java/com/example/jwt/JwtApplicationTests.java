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
		// var expireAt = LocalDateTime.now().plusMinutes(10);
		var expireAt = LocalDateTime.now().plusSeconds(20);

		var jwtToken = jwtService.create(claims, expireAt);

		System.out.println(jwtToken);
	}

	@Test
	void tokenValidation(){
		var token = "eaJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjo5MjMsImV4cCI6MTcwNTkwODMwNH0.ZPtYmd_nQz5Uc1S1uXOzbri4CptCELPJUl5hhYGBTGc";

		// 토큰 만료 예외
		/**
		 * io.jsonwebtoken.ExpiredJwtException: JWT expired at 2024-01-22T07:25:04Z. Current time: 2024-01-22T07:25:07Z, a difference of 3811 milliseconds.  Allowed clock skew: 0 milliseconds.
		 */

		// 토큰 변조 예외
		/**
		 * io.jsonwebtoken.MalformedJwtException: Malformed JWT JSON
		 * Caused by: io.jsonwebtoken.io.DeserializationException: Unable to deserialize bytes into a java.lang.Object instance: Unrecognized token 'y': was expecting (JSON String, Number, Array, Object or token 'null', 'true' or 'false')
		 */
		jwtService.validation(token);
	}

}
