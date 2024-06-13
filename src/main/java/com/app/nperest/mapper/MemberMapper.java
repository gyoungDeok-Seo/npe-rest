package com.app.nperest.mapper;

import com.app.nperest.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberMapper {
//    회원가입
    public void insert(MemberVO memberVO);
//    회원 정보 조회
    public Optional<MemberVO> select(String kakaoEmail);
//    회원 프로필 업데이트
    public void updateKakaoProfileUrl(MemberVO memberVO);
}
