package com.bs.spring.memo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.memo.model.service.MemoService;
import com.bs.spring.memo.model.vo.Memo;

@Controller
public class MemoController {
	@Autowired
	MemoService service;
	
	
	@RequestMapping("/memo/memo.do")
	public ModelAndView memo(ModelAndView mv) {
		List<Memo> list=service.selectMemoList();
		
		mv.addObject("list",list);
		mv.setViewName("memo/memo");
		return mv;
	}
	
	@RequestMapping("/memo/memoInsert.do")
	public ModelAndView memoInsert(Memo m,ModelAndView mv) {
		
	int result=service.memoInsert(m);
	
	String msg=result>0? "입력성공":"입력실패";
	String loc="/memo/memo.do";
	
	//ModelAndView 객체를 이용해서 데이터 전송 및 화면 이동하기
	//데이터를 저장할때 addObject(키,값);
	//화면 지정: setViewName("뷰이름");
	mv.addObject("msg",msg);
	mv.addObject("loc",loc);
	mv.setViewName("common/msg");
	//ViewResolver 로 감 , 즉 prefix,serfix 붙음 들어갈변수는 그냥 매소드들 리턴값이랑 똑같이
	//써주면됨
		
		return mv;
	}
	
}
