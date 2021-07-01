package com.kh.spring.demo.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.demo.model.vo.Dev;
@Controller
public class DemoController {

	
	@RequestMapping(value = "/demo/demo.do", method = RequestMethod.GET)
	public String demo() {
		return "demo/demo";
	}
	 
	/*
	 * 스프링에서 파라미터 값을 처리하는 방법 -> 맵핑매소드의 매개변수에 대해 알아보자 
	 * 스프링에서는 프론트에서 전송되는 데이터를 맵핑매소드의 매개변수로 받을 수 있음
	 * 맵핑매소드의 매개변수들은 주요 정보를 가지고 있는 객체, 파라미터값으로 선언이 가능함
	 * 맵핑배소드의 매개변수로 선언할 수 있는 변수들..
	 * ========= 설정없이 선언하면 자동으로 값이 대입되는 매개변수==========
	 * 1.HttpServletRequest
	 * 2.HttpServletResponse
	 * 3.HttpSession
	 * 4.java.util.Locale(지역정보)
	 * 5.InputStream/Reader
	 * 6.OutputStream/Writer
	 * ====== 데이터를 관리하는 객체
	 *7. VO객체 ->vo로 선언한 클래스를 선언, 자동으로 파라미터값으로 대입되서 생성됨
	 *	*파라미터의 key값과 vo객체의 맴버 변수명이 동일 해야함
	 *	*기본 자료형(String,배열가능)은 가능, 그외 생성된 객체는 불가능
	 *8.Model-> request의 데이터보관하는  기능을 하는 객체 -> 데이터를 보관하는 기능
	 *9.ModelAndView-> 데이터보관하는 Model의 기능, 화면정보를 보관하는 기능을 가지고 있는 객체
	 * *리턴값으로 사용 가능
	 * 
	 * =====어노테이션으로 옵션을 설정하는 매개변수=====
	 * 1.@RequestParam(value="파라미터 key값", 옵션...)변수선언
	 * 2.@RequestHeader(value="headerkey값", 옵션...)변수선언
	 * 3.@CookieValue(value="cookiekey값", 옵션...)변수선언
	 * 
	 * 
	 * ===추가적인 mapping Method에 선언하는 어노테이션======
	 * 1.@PathVariable("값")-> restful 방식의 url 요청이 왔을때 데이터를 받을때 사용
	 * 2.@ResponseBody-> 리턴해주는 값이 페이지가 아니라 데이터일때 JSON으로 파싱해서 보낼때 사용
	 * 
	 */
	
	//스프링 매핑 매소드 servlet의 doGet/Post()활용하기
	@RequestMapping("/demo/basicDemo.do")
	public String basicDemo(HttpServletRequest req,HttpServletResponse res,HttpSession session)throws Exception{
		System.out.println("request: "+req.getParameter("devName"));
		System.out.println("res: "+res.getClass());
		System.out.println("session: "+session.getId());
		
		Dev dev=new Dev();
		dev.setDevName(req.getParameter("devName"));
		dev.setDevAge(Integer.parseInt(req.getParameter("devAge")));
		dev.setDevEmail(req.getParameter("devEmail"));
		dev.setDevGender(req.getParameter("devGender"));
		dev.setDevLang(req.getParameterValues("devLang"));
		
		req.setAttribute("dev", dev);
		
		System.out.println(session.getAttribute("userId"));
		
		return "demo/demoResult";
/*		return "";*/
	}
	
	//@RequestParam 어노테이션을 이용해서 파라미터 값 받아오기
	@RequestMapping("/demo/reqParamDemo.do")
	public String reqParam(
		
			/*		@RequestParam(value="devName") String Name,
			@RequestParam(value="devAge",required=false,defaultValue="3") int Age,
			@RequestParam(value="devEmail") String Email,	
			@RequestParam(value="devGender",required=false) String Gender,
			@RequestParam(value="devLang",required=false) String[] Lang
			*/
			
			String devName,int devAge,String devEmail,String devGender,String[] devLang
			,Model m) {
		
		
		/*
		 * System.out.println(Name+" "+Age+" "+Email+" "+Gender);
		 * 
		 * for(String l: Lang) { System.out.println(l+""); }
		 */
		Dev dev=Dev.builder().devName(devName).devAge(devAge).devEmail(devEmail).devGender(devGender).devLang(devLang).build();
		
		m.addAttribute("dev",dev);//생존주기는 request 와 동일
		return "demo/demoResult";
		
	}
	
	//1.파라미터의 key 값과 command 객체의 멤버변수명이 동일해야함.
	//2.command객체에 다른객체가 멤버변수로 잇으면 잘못된 요청으로 exception이 발생함
	@RequestMapping("/demo/commandDemo.do")
	public String commandDemo(Dev dev,Model m) {
		System.out.println(dev);
		m.addAttribute("dev",dev);
		
		return "demo/demoResult";
	}
	
	//파라미터를 map으로 전달받기
	//기본자료형만 전달 받을 수 있음
	@RequestMapping("/demo/mapDemo.do")
	public String mapDemo(@RequestParam Map param, String[] devLang,Model m) {
		System.out.println(param);
		param.put("devLang", devLang);
		
		m.addAttribute("dev",param);
		
		return "demo/demoResult";
	}

	@RequestMapping("/demo/extraDemo.do")
	public String extraDemo(
			@CookieValue(value="time")String time,
			//@CookieValue(value="snack")String time
			@RequestHeader(value="Referer") String prevPage,
			@RequestHeader(value="user-agent") String userAgent
			
			) {
		
		System.out.println("Cookie: "+time);
		System.out.println("Referer: "+prevPage);
		System.out.println("User-agent: "+userAgent);
			
	
		return "demo/demoResult";
	}
	
}