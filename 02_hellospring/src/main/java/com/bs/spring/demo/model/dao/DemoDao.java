package com.bs.spring.demo.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.demo.model.vo.Dev;

public interface DemoDao {
	int insertDemo(SqlSessionTemplate session, Dev dev);
	
	List<Dev> selectDevList(SqlSessionTemplate session);
}
