package com.test.pro06.member.service;

import java.util.List;

import com.test.pro06.member.dto.MemberDTO;

public interface MemberService {

	List<MemberDTO> findAll();

	void save(MemberDTO dto);

	MemberDTO findbyId(String id);

	int merge(MemberDTO dto);

	int remove(String id);



	boolean login(String id, String pwd);

}
