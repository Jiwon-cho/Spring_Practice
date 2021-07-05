package com.bs.spring.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.member.model.dao.MemberDao;
import com.bs.spring.member.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao dao;
	
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public Member loingMember(Member mm) {
		
		
		return dao.loginMember(mm,session);
	}

	@Override
	public int insertMember(Member m) {
		// TODO Auto-generated method stub
		return dao.insertMember(session,m);
	}
	
	
	

}
