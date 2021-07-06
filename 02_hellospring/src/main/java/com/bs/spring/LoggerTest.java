package com.bs.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bs.spring.member.model.vo.Member;

public class LoggerTest {
	
	//log4j로 출력 메소드를 제공하는 클래스를 생성
	private Logger logger=LoggerFactory.getLogger(LoggerTest.class);
	
	public static void main(String[] args) {
		new LoggerTest().testLogger();
	}
	
	public void testLogger() {
		Member m=new Member();
		logger.debug(m.toString());
		logger.debug("{}",m);
		logger.debug("debug 출력");
		logger.info("info 출력");
		logger.warn("warn출력");
		logger.error("error출력");
		
	}

}
