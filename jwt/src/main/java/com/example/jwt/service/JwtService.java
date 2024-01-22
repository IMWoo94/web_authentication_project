package com.example.jwt.service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtService {

	private static String secretKey = "java17SpringBootJWTTokenIssueMethodMySecretKey";

	/**
	 * JWT 생성
	 */
	public String create(
		Map<String, Object> claims,
		LocalDateTime expireAt
	){
		var key = Keys.hmacShaKeyFor(secretKey.getBytes());
		// LocalDateTime -> Date 변환
		var _expireAt = Date.from(expireAt.atZone(ZoneId.systemDefault()).toInstant());

		return Jwts.builder()
			.signWith(key, SignatureAlgorithm.HS256)
			.setClaims(claims)
			.setExpiration(_expireAt)
			.compact();
	}

	/**
	 * JWT 검증
	 */
	public void validation(String token){
		var key = Keys.hmacShaKeyFor(secretKey.getBytes());

		var parser = Jwts.parserBuilder()
			.setSigningKey(key)
			.build();

		var result = parser.parseClaimsJws(token);

		result.getBody().entrySet().forEach(value ->{
			log.info("key : {}, value : {}", value.getKey(), value.getValue());
		});

	}

}
