package com.douzone.mysite.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.FileUploadService;
import com.douzone.mysite.service.GalleryService;
import com.douzone.mysite.vo.GalleryVo;

@RestController
@RequestMapping("/api/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	@Autowired
	private FileUploadService FileUploadService;
	
	@GetMapping("")
	public ResponseEntity<JsonResult> index() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JsonResult.success(galleryService.getGalleryImages()));
	}
	
	@PostMapping
	public ResponseEntity<JsonResult> upload(@RequestParam("file") MultipartFile file, GalleryVo galleyVo) {
		galleyVo.setUrl(FileUploadService.restoreImage(file));
		galleryService.addGalleryImage(galleyVo);
		
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(galleyVo));
	}
	
	@SuppressWarnings("serial")
	@DeleteMapping(value="/{no}")
	public ResponseEntity<JsonResult> delete(@PathVariable("no") Long no) {
		galleryService.deleteGalleryImage(no);
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(new HashMap<String, Long>(){{
			put("no", no);
		}}));
	}
}
