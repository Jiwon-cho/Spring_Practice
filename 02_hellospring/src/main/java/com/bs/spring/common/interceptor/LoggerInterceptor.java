package com.bs.spring.common.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		log.debug("==========interceptor 시작============");
		log.debug("==========매소드 시작============");
		log.debug("요청주소: "+request.getRequestURI());
		log.debug("==========interceptor끝=========");
		
		
		//return값이 true: mapping 매소드 실행
		//return값이 false: 멈춤
		/* return HandlerInterceptor.super.preHandle(request, response, handler); */
		/* response.sendRedirect("/spring/demo/demo.do"); */
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		log.debug("=========postHandle 시작===========");
		
		Map<String,Object> data=modelAndView.getModel();
		for(String key: data.keySet()) {
		log.debug("data:{}", data.get(key));
		}
		log.debug("전활될 화면이름:{}",modelAndView.getViewName());
		log.debug("==============postHandle끝===========");
		
		
		/*
		 * HandlerInterceptor.super.postHandle(request, response, handler,
		 * modelAndView);
		 */	
		}
	
	
	
	

}
