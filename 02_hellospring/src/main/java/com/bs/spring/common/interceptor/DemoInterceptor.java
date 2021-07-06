package com.bs.spring.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.bs.spring.member.model.vo.Member;


public class DemoInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		/* HttpSession session=request.getSession(false); */
		Member m=(Member)request.getSession().getAttribute("loginMember"); 
		
		boolean flag=true;
/*		if(session.getAttribute("loginMember")==null) {*/
		if(m==null) {
			request.setAttribute("msg", "로그인 후 사용가능");
			request.setAttribute("loc", "/");
			/* response.sendRedirect("/spring/msg"); */
		
			  request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(
			 request, response);
			 
			flag=false;
		}
		
		return flag;
	}

	
	
	
}
