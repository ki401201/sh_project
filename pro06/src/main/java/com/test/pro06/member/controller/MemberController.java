package com.test.pro06.member.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.pro06.member.dto.MemberDTO;

import jakarta.servlet.http.HttpSession;

public interface MemberController {
	String memberList(Model model);
	
	String joinMemberForm(@PathVariable("formName") String formName);
	String joinMember(MemberDTO dto, Model model);
	String detailMember(@RequestParam("id") String id, Model model);
	String updateMember(MemberDTO dto, Model model);
	String deleteMember(@RequestParam("id") String id, Model model);
	String login(@RequestParam("id") String id, @RequestParam("pwd") String pwd, Model model, HttpSession session);
	String logout(HttpSession session, Model model);
	
}
