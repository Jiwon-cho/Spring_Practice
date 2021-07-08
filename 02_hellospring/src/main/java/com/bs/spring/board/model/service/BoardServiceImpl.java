package com.bs.spring.board.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.board.model.dao.BoardDao;
import com.bs.spring.board.model.vo.Board;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardDao dao;
	
	@Autowired
	private SqlSession session;
	
	@Override
	public List<Board> selectBoardList(int cPage, int numPerpage) {
		// TODO Auto-generated method stub
		return dao.selectBoardList(session,cPage,numPerpage);
	}

	@Override
	public int selectBoardCount() {
		// TODO Auto-generated method stub
		return dao.selectBoardCount(session);
	}

	
	
}
