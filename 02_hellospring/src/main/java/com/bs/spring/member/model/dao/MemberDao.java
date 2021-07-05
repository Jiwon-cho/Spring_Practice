package com.bs.spring.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.member.model.vo.Member;

public interface MemberDao {
	Member loginMember(Member mm,SqlSessionTemplate session);
	int insertMember(SqlSessionTemplate session ,Member m);
}
