package com.bs.spring.memo.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bs.spring.memo.model.vo.Memo;

public interface MemoDao {
	
	int memoInsert(SqlSession session,Memo m);
	
	List<Memo> selectMemoList(SqlSession session);
} 
