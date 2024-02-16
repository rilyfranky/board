package com.myweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.domain.AuthVO;
import com.myweb.domain.MemberVO;
import com.myweb.mapper.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberMapper mapper;
	
	@Override
	public void register(MemberVO vo) throws Exception{
		mapper.register(vo);
	}

	@Override
	public void registerAuth(AuthVO vo) throws Exception {
		mapper.registerAuth(vo);		
	}

	@Override
	public int selectId(String userid) {
		int result = mapper.selectId(userid);
		log.info("result: " + result);
		return result;
	}

	
	
}
