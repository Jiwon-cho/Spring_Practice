package com.kh.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.service.dao.TestDao;

@Service
public class TestService {
	
	@Autowired
	private TestDao dao;
	
	public void test() {
		System.out.println("service 실행");
		dao.test();
	}

}