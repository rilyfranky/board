package com.myweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.domain.BoardAttachVO;
import com.myweb.domain.BoardVO;
import com.myweb.domain.Criteria;
import com.myweb.mapper.BoardAttachMapper;
import com.myweb.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


@Log4j
@Service
public class BoardServiceImpl implements BoardService {

	@Setter(onMethod_= @Autowired)
	private BoardMapper mapper;
	
	@Setter(onMethod_= @Autowired)
	private BoardAttachMapper attachMapper;
	
	
	@Transactional
	@Override
	public void register(BoardVO board) {
		log.info("register......" + board);
		
		mapper.insertSelectKey(board);
		
		if(board.getAttachList() == null || board.getAttachList().size() <= 0) {
			return;
		}
		
		board.getAttachList().forEach(attach ->{
			
			attach.setBno(board.getBno());
			attachMapper.insert(attach);
		});
	}

	
	@Transactional
	@Override
	public boolean modify(BoardVO board) {
		log.info("modify....." + board);
		
		attachMapper.deleteAll(board.getBno());
		
		boolean modifyResult = mapper.update(board) == 1;
		
		if(modifyResult && board.getAttachList() != null && board.getAttachList().size() > 0) {
			board.getAttachList().forEach(attach -> {
				attach.setBno(board.getBno());
				attachMapper.insert(attach);
			});
		}
		
		return modifyResult;
	}

	@Transactional
	@Override
	public boolean remove(Long bno) {
		log.info("remove....." + bno);
		
		attachMapper.deleteAll(bno);
		
		return mapper.delete(bno) == 1;
	}

//	@Override
//	public List<BoardVO> getList() {
//		log.info("getList.........");
//		
//		return mapper.getList();
//	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public BoardVO get(Long bno) {
		log.info("get......." + bno);
		mapper.updateView(bno);
		return mapper.read(bno);
	}

	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("get list with criteira...." + cri);
		
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		log.info("get total count");
		return mapper.getTotalCount(cri);
	}

	@Override
	public List<BoardAttachVO> getAttachList(Long bno) {
		
		log.info("get Attach list by bno " + bno);
		
		return attachMapper.findByBno(bno);
	}


	@Override
	public void updateView(Long bno) throws Exception {
		log.info("updateView : ");
		mapper.updateView(bno);
		
	}
	

}
