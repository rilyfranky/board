package com.myweb.service;

import com.myweb.domain.AuthVO;
import com.myweb.domain.MemberVO;

public interface MemberService {

	public void register(MemberVO vo) throws Exception;
	
	public void registerAuth(AuthVO vo) throws Exception;
	
	//중복 ID 검색
	public int selectId(String userid);
	
}
