package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class DeleteformAction implements Action {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String sno = request.getParameter("no");
		Long no = Long.parseLong(sno);

		new BoardDao().deleteByNo(no);

		MvcUtil.redirect(request.getContextPath() + "/board?page=1", request, response);
	}

}
