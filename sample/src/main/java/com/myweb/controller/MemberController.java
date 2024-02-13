package com.myweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myweb.domain.AuthVO;
import com.myweb.domain.MemberVO;
import com.myweb.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/member/*")
@Log4j
public class MemberController {

	@Autowired
	MemberService service;

	@Autowired
	BCryptPasswordEncoder pwdEncoder;

	@GetMapping(value = "/register")
	public void getRegister() throws Exception {

		log.info("get register");
	}

	@PostMapping(value = "/register")
	public String postRegister(MemberVO vo, AuthVO vo2) throws Exception {
		log.info("post register");

		// 비밀번호 인코딩
		String userpw = pwdEncoder.encode(vo.getUserpw());
		vo.setUserpw(userpw);

		service.register(vo);
		service.registerAuth(vo2);

		return null;
	}

	/*
	 * @Autowired BCryptPasswordEncoder pwdEncoder;
	 * 
	 * // 2. 회원 가입 POST (회원 가입 페이지에서 실제 회원 가입 버튼을 눌렀을 때 회원 가입 처리)
	 * 
	 * @PostMapping("/register") public String postRegister(MemberVO vo) {
	 * 
	 * log.info("post register");
	 * 
	 * // 비밀번호 암호화 처리 String userpw = pwdEncoder.encode(vo.getUserpw());
	 * vo.setUserpw(userpw);
	 * 
	 * int result = service.idCheck(vo); }
	 * 
	 * // 2-1. 회원 아이디 중복 여부를 확인한다. (Ajax 비동기 방식)
	 * 
	 * @PostMapping(value = "/idCheck", consumes = "application/json", produces = {
	 * MediaType.APPLICATION_JSON_UTF8_VALUE })
	 * 
	 * @ResponseBody public ResponseEntity<String> checkUserId(@RequestBody MemberVO
	 * vo) throws Exception { log.info("checking id......."); int result =
	 * service.checkUserId(vo.getUserid()); log.info("Checking Id Result : " +
	 * result); return result == 0 ? new ResponseEntity<>("success", HttpStatus.OK)
	 * : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }
	 * 
	 * // 2-2. 회원 사용자이름 중복 여부를 확인한다. (Ajax 비동기 방식)
	 * 
	 * @PostMapping(value = "/userNameCheck", consumes = "application/json",
	 * produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	 * 
	 * @ResponseBody public ResponseEntity<String> checkUserName(@RequestBody
	 * MemberVO vo) throws Exception { log.info("checking userName......."); int
	 * result = service.checkUserName(vo.getUserName());
	 * log.info("Checking userName Result: " + result); return result == 0 ? new
	 * ResponseEntity<>("success", HttpStatus.OK) : new
	 * ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }
	 */

}
