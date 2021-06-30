package com.kh.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.service.TestService;

@Controller
public class TestController {
	
	@Autowired
	private TestService service;

	@RequestMapping("/test")
	public String test() {
		System.out.println("controller실행");
		service.test();
		return "";
	}
}
