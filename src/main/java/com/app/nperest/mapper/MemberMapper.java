package com.app.nperest.mapper;

import com.app.nperest.domain.MemberSkillDTO;
import com.app.nperest.domain.MemberVO;
import com.app.nperest.domain.Search;
import com.app.nperest.domain.SkillVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {
//    회원가입
    public void insert(MemberVO memberVO);
//    회원 정보 조회
    public Optional<MemberVO> select(String kakaoEmail);
//    회원 프로필 업데이트
    public void updateKakaoProfileUrl(MemberVO memberVO);
//    회원 정보 수정
    public void updateMemberInfo(MemberVO memberVO);
//    회원 기술 조회
    public List<MemberSkillDTO> selectMemberSkill(String kakaoEmail);
//    기술 검색
    public List<SkillVO> selectSearchSkill(Search search);
//    회원 기술 추가
    public void insertMemberSkill(MemberSkillDTO memberSkillDTO);
//    회원 기술 삭제
    public void deleteMemberSkill(MemberSkillDTO memberSkillDTO);
}
