package com.app.nperest.service;

import com.app.nperest.domain.MemberSkillDTO;
import com.app.nperest.domain.MemberVO;

import java.util.List;
import java.util.Optional;

public interface MemberService {
//    회원가입
    public void join(MemberVO memberVO);
//    회원 정보 조회
    public Optional<MemberVO> getMember(String kakaoEmail);
//    회원 프로필 업데이트
    public void updateKakaoProfileUrl(MemberVO memberVO);
//    회원 기술 조회
    public List<MemberSkillDTO> getMemberSkill(String kakaoEmail);
}
