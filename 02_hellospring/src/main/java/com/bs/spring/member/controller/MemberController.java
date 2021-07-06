package com.bs.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bs.spring.member.model.service.MemberService;
import com.bs.spring.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
 @SessionAttributes({"loginMember"})
@Slf4j
public class MemberController {
	
	//private Logger logger=LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService ms;
	
	@Autowired
	private BCryptPasswordEncoder pwEncoder;
	
	
	@RequestMapping("/member/memberLogin.do")
	public String loginMember(Member m,HttpSession session,Model md) {
		log.debug(m.getUserId());
		log.debug(m.getPassword());
		
		
		Member mm=ms.loingMember(m);
		String msg=null;
		String loc="/";
		md.addAttribute("loc",loc);
	
		if(mm!=null) {
			//pwEncoder객체가 단방향으로 암호화된 값을 비교할 수 있게 메소드를 제공
			//matches(원본값,암호화된값); -> ture||false
			/*if(m.getPassword().equals(mm.getPassword())){*/
			if(pwEncoder.matches(m.getPassword(), mm.getPassword())) {
				md.addAttribute("loginMember", mm);
				msg="로그인 성공";
			}else {
				msg="로그인 실패";
			}
			
			//model객체는 기본 request scope를 갖는다.
			//@SessionAttributes를 이용해서 scope영역을 Session으로 확장할 수 있다.
			//@SessionAttributes 어노테이션은 클래스 선언부에 선언
			//@SessionAttributes({값 -> model key})
			
		
			// session.setAttribute("loginMember", mm); 
		
		
		}else {
			msg="아이디 비밀번호가 일치하지 않습니다";
		
		
			
			
		}
		md.addAttribute("msg",msg);
		
		return "common/msg";
		
	}
	
	@RequestMapping("/member/logout.do")
	public String logoutMember(HttpServletRequest request,HttpSession session,SessionStatus ss) {
		/*
		 * session=request.getSession(false); if(session!=null) { session.invalidate();
		 * }
		 */
		if(!ss.isComplete()) {
			ss.setComplete();
			
		}
		
		//return "index";// /WEB-INF/views/index.jsp
		return "redirect:/";//mapping주소를 작성
	}
	
	
	@RequestMapping("/member/enrollMember.do")
	public String memberEnroll() {
		return "member/memberEnroll";
	}
	
	@RequestMapping("/member/memberEnrollEnd.do")
	public String memberEnrollEnd(Member m, Model md) {
		/*
		 * System.out.println("암호화전: "+ m.getPassword());
		 * System.out.println("암호화후: "+pwEncoder.encode(m.getPassword()));
		 */
		log.debug("암호화전: {}",m.getPassword());
		log.debug("암호화후: {}",pwEncoder.encode(m.getPassword()));
		
		m.setPassword(pwEncoder.encode(m.getPassword()));
		
		int result=ms.insertMember(m);
		
		
		
		 String msg="";
		 String loc="";
		if(result>0) {
			msg="회원가입성공";
			loc="/";
			
		}else {
			msg="회원 가입 실패";
			loc="/member/enrollMember.do";
		}
		md.addAttribute("msg",msg);
		md.addAttribute("loc",loc);
		
		return "common/msg";
	
	}
	
	/*
	 * @RequestMapping("/msg") public String message( Model md) {
	 * md.addAttribute("msg","로그인 후 사용 가능합니다"); md.addAttribute("loc","/");
	 * 
	 * return "common/msg"; }
	 */
	
}
