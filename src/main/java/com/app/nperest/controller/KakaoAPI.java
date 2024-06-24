package com.app.nperest.controller;

import com.app.nperest.domain.MemberVO;
import com.app.nperest.service.KakaoService;
import com.app.nperest.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
@RequestMapping("/kakao")
@RequiredArgsConstructor
public class KakaoAPI {
    private final KakaoService kakaoService;

    private final MemberService memberService;

    @Value("82b31c9bfb2e3f34670907912b8aa30c")
    private String clientId;

    @Value("http://localhost:10000/kakao/login")
    private String redirectUri;

    @GetMapping("/login/end-point")
    public RedirectView loginKakao() {
        String kakaoAuthUrl = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="
                + clientId + "&redirect_uri=" + redirectUri;
        return new RedirectView(kakaoAuthUrl);
    }

    @GetMapping("/login")
    public RedirectView login(@RequestParam String code, HttpSession session){
        String token = kakaoService.getKakaoAccessToken(code);
        Optional<MemberVO> foundInfo = kakaoService.getKakaoInfo(token);

        if(foundInfo.isPresent()) {
            memberService.join(foundInfo.get());
//            MemberVO memberVO = memberService.getMemberByKakaoEmail(foundInfo.get().getKakaoEmail()).get();
//            session.setAttribute("member", memberVO);
        }
        return new RedirectView("http://localhost:3000/");
    }
}
