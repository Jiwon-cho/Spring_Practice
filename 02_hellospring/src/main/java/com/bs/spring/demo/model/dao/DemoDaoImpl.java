package com.bs.spring.demo.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.demo.model.vo.Dev;

@Repository
public class DemoDaoImpl implements DemoDao {

	@Override
	public int insertDemo(SqlSessionTemplate session, Dev dev) {
		// TODO Auto-generated method stub
		return session.insert("dev.insertDemo",dev);
	}

	@Override
	public List<Dev> selectDevList(SqlSessionTemplate session) {
		// TODO Auto-generated method stub
		return session.selectList("dev.selectDevList");
	}

	
	
}
