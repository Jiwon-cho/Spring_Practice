package com.bs.spring.demo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bs.spring.demo.model.service.DemoService;
import com.bs.spring.demo.model.vo.Dev;

@Controller
public class DemoController {

	@Autowired
	private DemoService service;
	
	
	@RequestMapping("/demo/demo.do")
	public String demo() {
		//페이지 전환용.... 
		System.out.println("demo Controller실행함!");
		return "demo/demo";
		//request.getRequest.Dispatcher("/WEB-INF/views/demo/demo.jsp").forward()
	}
	
	/* 스프링에서 파라미터값을 처리하는 방법 -> 맵핑매소드의 매개변수에 대해 알아보자.
	 * 스프링에서는 프론트에서 전송되는 데이터를 맵핑매소드의 매개변수로 받을 수 있음.
	 * 맵핑매소드의 매개변수들은 주요 정보를 가지고 있는 객체, 파라미터값으로 선언이 가능함
	 * 맵핑매소드의 매개변수로 선언할 수 있는 변수들......
	 * ----- 설정없이 선언하면 자동으로 값이 대입되는 매개변수 ------
	 * 1. HttpServletRequest
	 * 2. HttpServletResponse
	 * 3. HttpSession
	 * 4. java.util.Locale(지역정보)
	 * 5. InputStream/Reader
	 * 6. OutputStream/Writer
	 * ===== 데이터를 관리하는 객체 ==== 
	 * 7. VO객체 -> vo로 선언한 클래스를 선언 자동으로 파라미터값으로 대입되서 객체가 생성됨.
	 *    * 파라미터의 key값과 vo객체의 맴버변수명이 동일 해야함.
	 *    * 기본자료형(String, 배열가능)은 가능, 그외 생성되 객체는 불가능!
	 * 8. Model -> request의 데이터보관하는 기능을 하는 객체 -> 데이터를 보관하는 기능!
	 * 9. ModelAndView -> 데이터보관하는 Model의 기능, 화면정보를 보관하는 기능을 가지고 있는 객체
	 *    * 리턴값으로 사용가능
	 * 
	 * 
	 * ---- 어노테이션으로 옵션을 설정하는 매개변수 ------
	 * 1. @RequestParam(value="파라미터key값", 옵션...) 변수선언
	 * 2. @RequestHeader(value="headerkey값", 옵션...) 변수선언
	 * 3. @CookieValue(value="cookiekey값", 옵션....) 변수선언
	 * 
	 * 
	 * --- 추가적인 mapping Method에 선언하는 어노테이션 -----
	 * 1. @PathVariable("값") -> restful방식의 url요청이 왔을때 데이터를 받을때 사용
	 * 2. @ResponseBody -> 리턴해주는 값이 페이지가 아니라 데이터일때 JSON으로 파싱해서 보낼때사용
	 * 
	 */
	
	
	//스프링 매핑매소드 servlet의 doGet/Post()활용하기
	@RequestMapping("/demo/basicDemo.do")
	public String basicDemo(HttpServletRequest req, 
			HttpServletResponse res, HttpSession session) throws Exception {
		System.out.println("request : "+req.getParameter("devName"));
		System.out.println("response : "+res.getClass());
		System.out.println("session : "+session.getId());
		
		Dev dev=new Dev();
		dev.setDevName(req.getParameter("devName"));
		dev.setDevAge(Integer.parseInt(req.getParameter("devAge")));
		dev.setDevEmail(req.getParameter("devEmail"));
		dev.setDevGender(req.getParameter("devGender"));
		dev.setDevLang(req.getParameterValues("devLang"));
		
		req.setAttribute("dev", dev);
		
		
		System.out.println(session.getAttribute("userId"));
		
		//req.getRequestDispatcher("/WEB-INF/views/demo/demoResult.jsp").forward(req, res);
		return "demo/demoResult";
	}
	
	//@RequestParam어노테이션을 이용해서 파라미터값 받아오기
	@RequestMapping("/demo/reqParamDemo.do")
	public String reqParam(
//			@RequestParam(value="devName" ) String name,
//			@RequestParam(value="devAge",required=false, defaultValue="3") int age,
//			@RequestParam(value="devEmail" ) String email,
//			@RequestParam(value="devGender", required = false ) String gender,
//			@RequestParam(value="devLang", required = false ) String[] lang,
			String devName, int devAge, String devEmail,
			String devGender,String[] devLang,
			Model m
			) {
		//1. 파마미터의 key과 동일한 명칭으로 변수를 선언!
		//2. 선언한 파라미터는 모두 전송되어야함.
		System.out.println(devName+" "+devAge+" "+
			devEmail+" "+devGender);
		for(String l : devLang) {
			System.out.print(l+" ");
		}
		
		Dev dev=Dev.builder().devName(devName).devAge(devAge)
				.devEmail(devEmail).devGender(devGender).devLang(devLang).build();
		
		m.addAttribute("dev",dev);//생존주기는 기본적으로 request동일.
		
		
		return"demo/demoResult";
	}
	
	
	//아~ 왜케 힘들게하니. 그냥 vo에 넣어줘~
	//1. 파라미터의 key값과 command객체의 멤버변수명이 동일해야함.
	//2. command객체에 다른객체가 멤버변수로 있으면 잘못된 요청으로 익센션이 발생함.
	@RequestMapping("/demo/commandDemo.do")
	public String commandDemo(Dev dev,Model m) {
		System.out.println(dev);
		m.addAttribute("dev",dev);
		
		return "demo/demoResult";
	}
	
	
	//파라미터를 map으로 전달받기
	//기본자료형만 전달받을 수 있음
	@RequestMapping("/demo/mapDemo.do")
	public String mapDemo(@RequestParam Map param,String[] devLang, Model m) {
		System.out.println(param);
		
		param.put("devLang", devLang);
		
		m.addAttribute("dev",param);
		
		return"demo/demoResult";
	}
	
	@RequestMapping("/demo/extraDemo.do")
	public String extraDemo(
				@CookieValue(value="snack",required = false) String time,
				@RequestHeader(value="Referer") String prevPage,
				@RequestHeader(value="user-agent") String userAget
			) {
		System.out.println("Cookie : "+time);
		System.out.println("Referer : "+prevPage);
		System.out.println("user-agent : "+userAget);
		
		return "demo/demo";
	}
	
	
	@RequestMapping("/demo/insertDemo.do")
	public String insertDemo(Dev dev, Model m) {
		int result=service.insertDemo(dev);
		m.addAttribute("msg",result>0?"입력성공":"입력실패");
		m.addAttribute("loc","/demo/demo.do");
		return "common/msg";
	}
	
	@RequestMapping("/demo/demoList.do")
	public String demoList(Model m) {
		List<Dev> list=service.selectDevList();
		System.out.println(list);
		m.addAttribute("list",list);
		return "demo/demoList";
	}
	
}









