package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class WriteAction implements Action {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sno = request.getParameter("no");
		Long no = Long.parseLong(sno);
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		String user_no = request.getParameter("userNo");
		Long user = Long.parseLong(user_no);
		
		
		BoardVo vo = new BoardVo();
		// 게시글
		if (no==-1) {
			vo.setGroup_no(new BoardDao().maxgno()+1);
			vo.setOrder_no(1L);
			vo.setDepth(0L);
		}
		// 댓글
		else {
			vo=new BoardDao().findByNo(no);
			new BoardDao().updateNo(vo.getGroup_no(),vo.getOrder_no());
			vo.setOrder_no(vo.getOrder_no()+1);
			vo.setDepth(vo.getDepth()+1);
		}
		
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setUser_no(user);
		new BoardDao().insert(vo);
		
		MvcUtil.redirect(request.getContextPath() + "/board?page=1",request,response);
	}



}

