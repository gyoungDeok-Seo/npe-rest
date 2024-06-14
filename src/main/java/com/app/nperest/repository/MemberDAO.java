package com.app.nperest.repository;

import com.app.nperest.domain.MemberSkillDTO;
import com.app.nperest.domain.MemberVO;
import com.app.nperest.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberDAO {
    private final MemberMapper memberMapper;
//    회원가입
    public void save(MemberVO memberVO){
        memberMapper.insert(memberVO);
    }
//    회원 정보 조회
    public Optional<MemberVO> findByKakaoEmail(String kakaoEmail){
        return memberMapper.select(kakaoEmail);
    };
//    회원 프로필 업데이트
    public void updateKakaoProfileUrl(MemberVO memberVO){
        memberMapper.updateKakaoProfileUrl(memberVO);
    };
    //    회원 기술 조회
    public List<MemberSkillDTO> findByKakaoEmailForMemberSkill(String kakaoEmail){
        return memberMapper.selectMemberSkill(kakaoEmail);
    };
}
