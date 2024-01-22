package com.example.cookie.service;

import org.springframework.stereotype.Service;

import com.example.cookie.db.UserRepository;
import com.example.cookie.model.LoginRequest;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	// login logic
	public void login(
		LoginRequest loginRequest,
		HttpServletResponse response
	){
		var id = loginRequest.getId();
		var pw = loginRequest.getPassword();

		var optionalUser = userRepository.findByName(id);
		if(optionalUser.isPresent()){
			var userDto = optionalUser.get();
			if(userDto.getPassword().equals(pw)){
				// cookie 에 해당 정보를 저장
				var cookie = new Cookie("authorization-cookie", userDto.getId());
				cookie.setDomain("localhost"); // naver.com, daum.net -> 입력된 도메인에 대해서만 해당 cookie 를 전달
				cookie.setPath("/");
				cookie.setMaxAge(-1); // -1 의 경우 Session 과 동일하게
				// 쿠키를 사용할 때 자바스크립트 등으로 탈취되지 않도록 옵션을 부여 / setHttpOnly 옵션을 부여 시 브라우저에서 해당 쿠키로는 더이상 접근이 불가능하다.
				// XSS 와 같은 공격을 예방할 수 있지만, 클라이언트에서 서버로 전달되는 패킷을 가로채는 해킹방법 ( 스니핑 ) 은 예방이 불가하다.
				// 자바스크립트 공격은 막을 수 있지만 네트워크를 감청하는 작업은 막을 수 없다. 이를 막기 위해서 나온 것이 SSL 암호화가 적용된다.
				cookie.setHttpOnly(true);
				// HTTPS 통신에만 Cookie 를 전송해야하는 경우 setSecure 옵션을 주면 Http 통신에는 cookie 를 전송하지 않습니다.
				cookie.setSecure(true);

				// 응답 정보에 cookie 등록 -> 즉, 클라이언트에 쿠키 전달
				response.addCookie(cookie);
			}
		}else{
			throw new RuntimeException("User Not Found");
		}

	}
}
