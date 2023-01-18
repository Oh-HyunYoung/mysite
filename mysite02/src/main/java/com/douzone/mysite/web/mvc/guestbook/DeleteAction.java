package com.douzone.mysite.web.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.GuestbookDao;
import com.douzone.web.mvc.Action;

public class DeleteAction implements Action {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	String sno = request.getParameter("no");
	Long no = Long.parseLong(sno);
	String password = request.getParameter("password");

	new GuestbookDao().deleteByNoAndPassword(no, password);
	
	response.sendRedirect(request.getContextPath()+"/guestbook");
	}
}
