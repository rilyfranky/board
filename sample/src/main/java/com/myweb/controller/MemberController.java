package com.myweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	    
		if (service.selectId(vo.getUserid()) != 0) {
			log.info("SIGN UP FAILED.........");
			return "/member/register";
		} else {
			service.register(vo);
			service.registerAuth(vo2);
			log.info("SIGN UP SUCCEESS......");
		}


		return null;
	}

	@PostMapping("/checkId")
	@ResponseBody
	public int checkId(@RequestParam("userid") String userid) {
		log.info("checkId.....");
		log.info("userid : " + userid);
		int result = service.selectId(userid);
		
		return result;
	}

}
