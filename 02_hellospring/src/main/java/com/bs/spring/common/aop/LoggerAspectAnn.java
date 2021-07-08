package com.bs.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

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
	
	
	@Around("execution(* com.bs.spring..*(..))")
	public Object aroundTest(ProceedingJoinPoint join) throws Throwable{
		
		//전처리 로직,후 처리 로직이 동시에 있음
		//기준: join.proceed(); 실행 시점
		Signature sig=join.getSignature();
		//전처리 로직
		/*
		 * log.debug("=========== around AOP 전 처리========== ");
		 * log.debug("매소드: "+sig.getName()+" : "+"클래스: "+sig.getDeclaringTypeName());
		 * log.debug("=======================================");
		 */
		
		Object obj=join.proceed();
		
		//후처리 로직
		log.debug("=========== around AOP 후 처리========== ");
		log.debug("매소드: "+sig.getName()+" : "+"클래스: "+sig.getDeclaringTypeName());
		log.debug("=======================================");
		
		
		return obj;
	}
	
	//Around AOP를 이용해서 성능 측정하기
	@Around("execution(* com.bs.spring..*Dao.*(..))")
	public Object daoCheckRuntime(ProceedingJoinPoint join)throws Throwable {
		log.debug("===========dao 성능 측정===========");
		StopWatch stop=new StopWatch();
		stop.start();
		Object obj=join.proceed();
		log.debug("=======측정 결과==========");
		stop.stop();
		Signature sig=join.getSignature();
		log.debug(sig.getName()+" 소요시간: "+stop.getTotalTimeMillis()+"ms");
		
		
		
		return obj;
		
	}
	
	
}
