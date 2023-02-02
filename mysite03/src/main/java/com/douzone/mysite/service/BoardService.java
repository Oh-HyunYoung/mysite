package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {
	private static final int LIST_SIZE = 5; // 리스팅되는 게시물의 수
	private static final int PAGE_SIZE = 5; // 페이지 리스트의 페이지 수
	
	@Autowired
	private BoardRepository boardRepository;
	
	public List<BoardVo> getList() {
		return boardRepository.findAll();
	}
	
	public void addContents(BoardVo vo) {
		boardRepository.insertByContent(vo);
	}
	
	public BoardVo getContents(Long no) {
		return boardRepository.findByNo(no);
	}
	
	public BoardVo getContents(Long no, Long UserNo) {
		return null;
	}
	
	public void updateContents(BoardVo vo) {
		boardRepository.updateByContents(vo);
	}
	
	public void deleteContents(Long no) {
		boardRepository.deleteByNo(no);
	}

	public Long maxgno() {
		return boardRepository.maxgno();
	}
	
	public void updateNo(Long gno,Long ono) {
		boardRepository.updateNo(gno, ono);
	}



//	public Map<String, Object> getContentsList(int page, String keyword) {
//		int toTalCount = boardRepository.getTotalCount(keyword);  
//		
//		
//		// 1. view에서 게시판 리스트를 렌더링 하기 위한 데이터 값 계산
//		int beginPage = 0;
//		int prevPage = 0;
//		int nextPage = 0;
//		int endPage = 0;
//		
//		// 2. 리스트 가져오기
//		List<BoardVo> list = boardRepository.findAllByPageAndKeyWord(page,keyword, LIST_SIZE);
//		
//		// 3. 리스트 정보를 맵에 저장
//		Map<String,Object> map = new HashMap<>();
//		map.put("list", list);
//		map.put("beginPage", beginPage);
//		map.put("prevPage", prevPage);
//		map.put("nextPage", nextPage);
//		map.put("endPage", endPage);
//		return map;
//	}
}
