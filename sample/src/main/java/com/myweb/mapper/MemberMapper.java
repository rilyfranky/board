package com.myweb.mapper;

import com.myweb.domain.AuthVO;
import com.myweb.domain.MemberVO;

public interface MemberMapper {

	public MemberVO read(String userid);
	
	public int register(MemberVO vo);
	
	public int registerAuth(AuthVO vo);
	
	//중복 ID 검색
	public int selectId(String userid);
	
}
