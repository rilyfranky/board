package com.myweb.mapper;

import java.util.List;

import com.myweb.domain.BoardVO;
import com.myweb.domain.Criteria;

public interface BoardMapper {
	
	//xml에서 sql문을 처리했기때문에 필요 없음
	//@Select("select * from tbl_board where bno > 0")
	public List<BoardVO> getList();
	
	public List<BoardVO> getListWithPaging(Criteria cri);

	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno);
	
	public int delete(Long bno);
	
	public int update(BoardVO board);
}
