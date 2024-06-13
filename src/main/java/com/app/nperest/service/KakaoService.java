package com.app.nperest.service;

import com.app.nperest.domain.MemberVO;

import java.util.Optional;

public interface KakaoService {
//    카카오 엑세스 토큰 조회
    public String getKakaoAccessToken(String code);
//    카카오 회원 정보 조회
    public Optional<MemberVO> getKakaoInfo(String token);
}
