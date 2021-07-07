package com.bs.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LoggerAspectAnn {

	@Pointcut("execution(* com.bs.spring..service.*.*(..))")
	public void service() {};
	
	@Pointcut("execution(* com.bs.spring.demo..*(..))")
	public void demo() {};
	
	
	
	
	
	@Before("service()")
	public void loggerTestAnn(JoinPoint jp) {
			log.debug("===========어노테이션 aspect==========");
			Signature sig=jp.getSignature();
			log.debug(sig.getName()+" : "+sig.getDeclaringTypeName());
			log.debug("================================");
	}
	
	
	@After("demo()")
	public void loggerDemo(JoinPoint jp) {
			log.debug("===========어노테이션 aspectDemo==========");
			Object[] param=jp.getArgs();
			for(Object p: param) {
				log.debug("param:{}",p);
			}
			log.debug("======이건 분계선====");
			Signature sig=jp.getSignature();
			log.debug(sig.getName()+" : "+sig.getDeclaringTypeName());
			log.debug("================================");
	}
	
}
