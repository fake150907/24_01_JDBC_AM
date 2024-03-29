package com.KoreaIT.java.JDBCAM.session;

import com.KoreaIT.java.JDBCAM.dto.Member;

public class Session {

	public Member loginedMember;
	public int loginedMemberId;

	public Session() {
		loginedMemberId = -1;
	}

	public void login(Member member) {
		loginedMember = member;
		loginedMemberId = member.getId();
	}

	public void logout() {
		loginedMember = null;
		loginedMemberId = -1;
	}

	public boolean isLogined() {
		return loginedMemberId != -1;
	}

	public String getMemberName() {
		return loginedMember.getName();
	}

}