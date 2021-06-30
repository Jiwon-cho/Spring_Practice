package com.kh.spring;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextTest2 {
	@Bean
	@Qualifier("p2")
	public Person getEmp() {
		return new Person();
	}
}
