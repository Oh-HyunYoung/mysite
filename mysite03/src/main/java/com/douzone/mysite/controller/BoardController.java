package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;


@Controller
@RequestMapping("/board")
public class BoardController{
	
	@Autowired
	private BoardService boardService;
	
//	@RequestMapping("/")
//	public String index(Model model) {
//		Map<String, Object> map = boardService.getContentsList(1,"");
//
//		model.addAttribute("map",map);
////		model.addAllAttributes(map);
//	
//		return "board/index";
//	}
	@RequestMapping("")
	public String index(Model model) {
		List<BoardVo> index = boardService.getList();
		model.addAttribute("index", index);
		return "board/index";
	}

	
	@RequestMapping(value="/view/{no}", method=RequestMethod.GET)
	public String view(@PathVariable("no") Long no, Model model) {
		model.addAttribute("no",no);
		System.out.println("get"+no);
		return "board/view";
	}
	
	@RequestMapping(value="/view/{no}", method=RequestMethod.POST)
	public String view(@PathVariable("no") Long no) {
		boardService.getContents(no);
		System.out.println("post"+no);
		return "redirect:/board";
	}
	
	
//	@RequestMapping(value="/write",method=RequestMethod.POST)
//	public String insert(BoardVo vo) {
//		boardService.addContents(vo);
//		return "redirect:/board/write";
		
//	}	
}