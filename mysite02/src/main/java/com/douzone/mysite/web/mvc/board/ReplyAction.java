package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ReplyAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String sno = request.getParameter("no");
//		Long no = Long.parseLong(sno);

//String name = request.getParameter("name");
//		String nhit = request.getParameter("hit");
//		Long hit = Long.parseLong(nhit);
//		String regDate = request.getParameter("regDate");
//		String title = request.getParameter("title");
//		String contents = request.getParameter("content");
//		
//		String sno = request.getParameter("userNo");
//		Long userNo = Long.parseLong(sno);
//
//		BoardVo vo = new BoardVo();
//		vo.setTitle(title);
//		vo.setContents(contents);
//		vo.setUser_no(userNo);
//		vo.setHit(hit);
//		vo.setReg_date(regDate);
//vo.setName(name);

		

//		new BoardDao().insertByContent(vo);

		MvcUtil.forward("board/reply", request, response);
//		MvcUtil.redirect(request.getContextPath() + "/board?a=reply",request,response);
	}
}
