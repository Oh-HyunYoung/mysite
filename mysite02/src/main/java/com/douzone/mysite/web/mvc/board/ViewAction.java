package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ViewAction implements Action {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String sno = request.getParameter("no");
		Long no = Long.parseLong(sno);
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");


		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(contents);
		
		BoardVo svo = new BoardDao().findByNo(no);
		new BoardDao().updatehit(no);
		request.setAttribute("vo", svo);
		
		MvcUtil.forward("board/view", request, response);
	}
}