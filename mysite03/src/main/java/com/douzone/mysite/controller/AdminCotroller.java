package com.douzone.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.vo.UserVo;

@Auth(role="ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminCotroller {
	
	@RequestMapping("")
	public String main(@AuthUser UserVo authUser) {
		
		return "admin/main";
	}

	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}

	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}

	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
}
