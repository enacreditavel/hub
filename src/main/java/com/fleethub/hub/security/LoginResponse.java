package com.fleethub.hub.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
	private String token;
	private Long id;
	private String userName;
}
