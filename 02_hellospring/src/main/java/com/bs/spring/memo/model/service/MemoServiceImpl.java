package com.bs.spring.memo.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.memo.model.dao.MemoDao;
import com.bs.spring.memo.model.vo.Memo;

@Service
public class MemoServiceImpl implements MemoService {
	@Autowired
	private SqlSession session;
	
	@Autowired
	private MemoDao dao;
	
	
	
	@Override
	public int memoInsert(Memo m) {
		int result=dao.memoInsert(session,m);
		
		return result;
	}



	@Override
	public List<Memo> selectMemoList() {
		// TODO Auto-generated method stub
		List<Memo> list=dao.selectMemoList(session);
		
		return list;
	}
	
}
