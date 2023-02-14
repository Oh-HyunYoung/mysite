package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GalleryVo;

@Repository
public class GalleryRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public List<GalleryVo> findImages() {
		return sqlSession.selectList("gallery.findImages");
	}

	public void deleteByImages(Long no) {
		sqlSession.delete("gallery.deleteByImages",no);
	}

	public void insertByUrl(GalleryVo vo) {
		sqlSession.insert("gallery.insertByUrl",vo);
	}
}
