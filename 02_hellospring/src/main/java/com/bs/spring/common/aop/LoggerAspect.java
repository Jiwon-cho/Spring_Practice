package com.bs.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerAspect {
	
	//Before advice 이용하기
	public void logTest(JoinPoint jp) {
		//메소드명,클래스명을 확인 할 수 있는 signature객체
		Signature sig=jp.getSignature();
		log.debug("=========AOP logTest 시작==============");
		log.debug("메소드: "+sig.getName()+" 클래스:"+sig.getDeclaringTypeName());
		log.debug("======================================");
	}

}
