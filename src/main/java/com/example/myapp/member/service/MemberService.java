package com.example.myapp.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myapp.member.dao.IMemberRepository;
import com.example.myapp.member.model.Member;

@Service
public class MemberService implements IMemberService {
	
	@Autowired
	IMemberRepository memberRepository;

	@Override
	public void insertMember(Member member) {
		// TODO Auto-generated method stub
		memberRepository.insertMember(member);

	}

	@Override
	public Member selectMember(String userid) {
		// TODO Auto-generated method stub
		return memberRepository.selectMember(userid);
	}

	@Override
	public List<Member> selectAllMembers() {
		// TODO Auto-generated method stub
		return memberRepository.selectAllMembers();
	}

	@Override
	public void updateMember(Member member) {
		// TODO Auto-generated method stub
		memberRepository.updateMember(member);

	}

	@Override
	public void deleteMember(Member member) {
		// TODO Auto-generated method stub
		memberRepository.deleteMember(member);

	}

	@Override
	public String getPassword(String userid) {
		// TODO Auto-generated method stub
		return memberRepository.getPassword(userid);
	}

}
