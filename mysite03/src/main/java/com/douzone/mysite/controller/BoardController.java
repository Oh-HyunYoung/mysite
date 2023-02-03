package com.douzone.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;


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

	
	@RequestMapping(value="/view/{no}", method= {RequestMethod.GET, RequestMethod.POST })
	public String view(@PathVariable("no") Long no, Model model) {
		BoardVo boardvo = boardService.getContents(no);
		model.addAttribute("boardvo",boardvo);
		return "board/view";
	}
	
	@RequestMapping(value="/modify/{no}", method= RequestMethod.GET)
	public String modify(@PathVariable("no") Long no, Model model) {
		BoardVo boardvo = boardService.getContents(no);
		model.addAttribute("boardvo",boardvo);
		return "board/modify";
	}
	
	@RequestMapping(value="/modify/{no}",method=RequestMethod.POST)
	public String modify(@PathVariable("no") Long no, BoardVo vo, Model model) {
		boardService.updateContents(vo);
		return "redirect:/board/view/"+no;
	}
	
	@RequestMapping(value="/delete/{no}")
	public String delete(@PathVariable("no") Long no, Model model) {
		boardService.deleteContents(no);
		return "redirect:/board";
	}
	
	@RequestMapping(value="/write/{no}", method=RequestMethod.GET)
	public String write(@PathVariable("no") Long no, Model model) {
		model.addAttribute("number",no);
		System.out.println("number: "+ no);
		return "board/write";
	}
	
	@RequestMapping(value="/write",method=RequestMethod.POST)
	public String write(BoardVo vo, HttpSession session,Long no) {
		System.out.println("vo: "+vo);
		if(no == -1) {
			System.out.println("vo -1 : "+ vo);
			vo.setG_no(boardService.maxgno()+1);
			vo.setO_no(1L);
			vo.setDepth(0L);
		} else {
			BoardVo vos = boardService.getContents(no);
			System.out.println("else : "+ vos);
			boardService.updateNo(vos.getG_no(), vos.getO_no());
			vo.setG_no(vos.getG_no());
			vo.setO_no(vos.getO_no()+1);
			vo.setDepth(vos.getDepth()+1);
		}

		UserVo authuser = (UserVo)session.getAttribute("authUser");
		vo.setUser_no(authuser.getNo());
		boardService.addContents(vo);
		
		return "redirect:/board";	
	}	
}