package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
//	public List<BoardVo> findAllByPageAndKeyWord(int page, String keyword, int size) {
//		Map<String, Object> map = new HashMap<>();
//		map.put("startOffset", (page-1)*size);
//		map.put("size", size);
//		map.put("keyword", keyword);
//		
//		return sqlSession.selectList("board.findAllByPageAndKeyWord", map);
//	}

	public int getTotalCount(String keyword) {
		return sqlSession.selectOne("board.getTotalCount", keyword);
	}
	
	public void insertByContent(BoardVo vo) {
		sqlSession.insert("board.insertByContent",vo);
	}

	public List<BoardVo> findAll(){
		return sqlSession.selectList("board.findAll");
	}
	
	public void deleteByNo(Long no) {
		sqlSession.delete("board.deleteByNo",no);
	}

	public BoardVo findByNo(Long no) {
		return sqlSession.selectOne("board.findByNo",no);
	}
	
	public void updateByContents(BoardVo vo) {
		sqlSession.selectOne("board.updateByContents",vo);
	}

	public Long maxgno() {
		return sqlSession.selectOne("board.maxgno");
	}
	
	public void updateNo(Long gno, Long ono) {
		Map<String,Long> map = Map.of("gno",gno,"ono",ono);
		List<BoardVo> list = sqlSession.selectList("board.selectNo",map);
		System.out.println("list: "+ list);
		for(BoardVo vo:list) {
			vo.setO_no(vo.getO_no()+1);
			sqlSession.selectOne("board.updateNo",vo);
		}
	}
	
}
