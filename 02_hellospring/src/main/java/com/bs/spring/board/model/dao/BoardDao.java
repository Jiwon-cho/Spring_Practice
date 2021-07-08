package com.bs.spring.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bs.spring.board.model.vo.Board;

public interface BoardDao {
	
	List<Board> selectBoardList(SqlSession session,int cPage, int numPerpage);
	
	int selectBoardCount(SqlSession session);
}
