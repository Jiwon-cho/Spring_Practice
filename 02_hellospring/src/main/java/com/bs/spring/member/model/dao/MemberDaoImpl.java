package com.bs.spring.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.member.model.vo.Member;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Override
	public Member loginMember(Member mm,SqlSessionTemplate session) {
		
		return session.selectOne("member.loginMember",mm);
	}

	@Override
	public int insertMember(SqlSessionTemplate session, Member m) {
		// TODO Auto-generated method stub
		return session.insert("member.insertMember",m);
	}
	
	

}
