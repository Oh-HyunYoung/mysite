package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.SiteService;

@Auth(role="ADMIN")
@RestController
@RequestMapping("/api/main")
public class AdminController {


	@Autowired
	private SiteService siteService;
	
	@GetMapping("")
	public ResponseEntity<JsonResult> main() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JsonResult.success(siteService.getSite()));
	}
	
//	@RequestMapping(value = "/main/update", method=RequestMethod.POST)
//	public String update(SiteVo vo,
//			@RequestParam("file") MultipartFile file) {
//		String profile = fileuploadService.restore(file);
//		if(profile != null) {
//			vo.setProfile(profile);
//		}
//		
//		SiteVo site = applicationContext.getBean(SiteVo.class);
//		
//		siteService.updateSite(vo);
//		servletContext.setAttribute("sitevo", vo);
//		BeanUtils.copyProperties(vo,site);
//		
//		return "redirect:/admin";
//	}
	

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
