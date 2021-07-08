package com.bs.spring.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bs.spring.board.model.vo.Board;

@Repository
public class BoardDaoImpl implements BoardDao{

	@Override
	public List<Board> selectBoardList(SqlSession session, int cPage, int numPerpage) {
		// TODO Auto-generated method stub
		return session.selectList("board.selectBoardList",null,new RowBounds((cPage-1)*numPerpage,numPerpage));
	}

	@Override
	public int selectBoardCount(SqlSession session) {
		// TODO Auto-generated method stub
		return session.selectOne("board.selectBoardCount");
	}
	
	

}
