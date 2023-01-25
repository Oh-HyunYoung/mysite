package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class WriteformAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		//String name = request.getParameter("name");
		String nhit = request.getParameter("hit");
		Long hit = Long.parseLong(nhit);
		String regDate = request.getParameter("regDate");
		//String uno = request.getParameter("user_no");
		//Long user_no = Long.parseLong(uno);
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setHit(hit);
		vo.setReg_date(regDate);
		//vo.setName(name);
		
		//vo.setUser_no(user_no);
		
		new BoardDao().insertByContent(vo);
		MvcUtil.forward("board/write", request, response);
	}

}
