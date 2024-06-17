package com.app.nperest.service;

import com.app.nperest.domain.MemberSkillDTO;
import com.app.nperest.domain.MemberVO;
import com.app.nperest.domain.Search;
import com.app.nperest.domain.SkillVO;

import java.util.List;
import java.util.Optional;

public interface MemberService {
//    회원가입
    public void join(MemberVO memberVO);
//    회원 정보 조회
    public Optional<MemberVO> getMember(String kakaoEmail);
//    회원 프로필 업데이트
    public void updateKakaoProfileUrl(MemberVO memberVO);
//    회원 정보 수정
    public void modifyMemberInfo(MemberVO memberVO);
//    회원 기술 조회
    public List<MemberSkillDTO> getMemberSkill(String kakaoEmail);
//    기술 검색
    public List<SkillVO> getSearchSkillList(Search search);
//    회원 기술 추가
    public void saveMemberSkill(MemberSkillDTO memberSkillDTO);
//    회원 기술 삭제
    public void dropMemberSkill(MemberSkillDTO memberSkillDTO);
}
