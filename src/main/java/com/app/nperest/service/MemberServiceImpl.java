package com.app.nperest.service;

import com.app.nperest.domain.MemberVO;
import com.app.nperest.repository.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Primary
public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO;
//    회원가입
    @Override
    public void join(MemberVO memberVO) {
//        유무 검사를 위해 Optional 객체로 생성
        Optional<MemberVO> foundMember = getMember(memberVO.getKakaoEmail());
//        1. 최초 로그인 검사
        if (foundMember.isEmpty()){
            memberVO.setMemberPosition(" ");
            memberDAO.save(memberVO);
        } else { // 이메일 정보가 있을 경우
            MemberVO member = foundMember.get();
            String oldUrl = member.getKakaoProfileUrl();
            String newUrl = memberVO.getKakaoProfileUrl();
            if (!Objects.equals(oldUrl, newUrl)) { // 기존 프로필과 다를 경우
                member.setKakaoProfileUrl(newUrl);
                updateKakaoProfileUrl(member);
            }
        }
    }
//    회원 정보 조회
    @Override
    public Optional<MemberVO> getMember(String kakaoEmail){
        return memberDAO.findByKakaoEmail(kakaoEmail);
    }
//    회원 프로필 업데이트
    @Override
    public void updateKakaoProfileUrl(MemberVO memberVO){
        memberDAO.updateKakaoProfileUrl(memberVO);
    };
}
