package com.myweb.security;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.log4j.Log4j;


/* 
 * 스프링 시큐리티 5부터 NoOpPasswordEncoder를 사용할 수 없기때문에 직접 암호화가 없는 PasswordEncoder를 구현해서 사용.
 */
@Log4j
public class CustomNoOpPasswordEncoder implements PasswordEncoder{

	public String encode(CharSequence rawPassword) {
		log.warn("before encode: " + rawPassword);
		
		return rawPassword.toString();
	}
	
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		log.warn("mathces: " + rawPassword + ":" + encodedPassword);
		
		return rawPassword.toString().equals(encodedPassword);
	}
}
