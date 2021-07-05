package com.bs.spring.member.model.service;

import com.bs.spring.member.model.vo.Member;

public interface MemberService {

	Member loingMember(Member mm);
	int insertMember(Member m);
}
