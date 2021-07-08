package com.bs.spring.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.board.model.service.BoardService;
import com.bs.spring.common.PageFactory;

@Controller
public class BoardController {

		@Autowired
		private BoardService service;
		
		@RequestMapping("/board/boardList.do")
		public  ModelAndView boardList(
				@RequestParam(value="cPage", defaultValue="1") int cPage,
				@RequestParam(value="numPerpage", defaultValue="5") int numPerpage,
				ModelAndView mv) {
			mv.addObject("list",service.selectBoardList(cPage,numPerpage));
			int totalData=service.selectBoardCount();
			mv.addObject("totalContents", totalData);
			mv.addObject("pageBar",PageFactory.getPageBar(totalData, cPage, numPerpage, "boardList.do"));
			mv.setViewName("board/boardList");
			return mv;
		
		}
	
}
