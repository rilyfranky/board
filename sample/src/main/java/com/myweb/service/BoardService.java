package com.myweb.service;

import java.util.List;

import com.myweb.domain.BoardAttachVO;
import com.myweb.domain.BoardVO;
import com.myweb.domain.Criteria;

public interface BoardService {

	
	public void register(BoardVO board);
	
	public BoardVO get(Long bno);
	
	public boolean modify(BoardVO board);
	
	public boolean remove(Long bno);
	
	//public List<BoardVO> getList();
	
	public List<BoardVO> getList(Criteria cri);
	
	public int getTotal(Criteria cri);
	
	public List<BoardAttachVO> getAttachList(Long bno);
	
	public void updateView(Long bno) throws Exception;

}
