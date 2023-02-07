package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.FileuploadService;
import com.douzone.mysite.service.GalleryService;
import com.douzone.mysite.vo.GalleryVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	@Autowired
	private FileuploadService fileuploadService;
	
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping("")
	public String index(Model model) {
		List<GalleryVo> list = galleryService.getImages();
		model.addAttribute("list", list);
		return "gallery/index";
	}
	
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no") Long no) {
		galleryService.removeImage(no);
		return "redirect:/gallery";
	}
	
	@RequestMapping("/upload")
	public String upload(GalleryVo vo,
			@RequestParam("file") MultipartFile file,
			@RequestParam("comments") String comments,
			Model model) {
		
		String url = fileuploadService.restore(file);
		model.addAttribute("url", url);
		vo.setUrl(url);
		vo.setComments(comments);
		galleryService.addImage(vo);
		return "redirect:/gallery";
	}
}
