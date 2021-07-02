package com.bs.spring.demo.model.service;

import java.util.List;

import com.bs.spring.demo.model.vo.Dev;

public interface DemoService {
	
	int insertDemo(Dev dev);
	
	List<Dev> selectDevList();
}
