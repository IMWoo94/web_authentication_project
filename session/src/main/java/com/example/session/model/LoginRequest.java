package com.example.session.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
// Json 데이터와 Java 객체간의 변수 네이밍 매칭
// Json 은 대체적으로 SnakeCase 형식으로 데이터를 전달하는데, Java 는 CamelCase 방식을 사용한다.
// 해당 어노테이션을 통해서 해당 객체의 모든 필드 네이밍을 SnakeCase 로 새로 네이밍하여 매칭할 수 있다.
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LoginRequest {

	private String id;
	private String password;
}
