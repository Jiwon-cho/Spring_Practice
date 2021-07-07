package com.bs.spring.memo.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bs.spring.memo.model.vo.Memo;

@Repository
public class MemoDaoImpl implements MemoDao {

	@Override
	public int memoInsert(SqlSession session, Memo m) {
		// TODO Auto-generated method stub
		return session.insert("memo.memoInsert",m);
	}

	@Override
	public List<Memo> selectMemoList(SqlSession session) {
		// TODO Auto-generated method stub
		return session.selectList("memo.selectMemoList");
	}

	
}
