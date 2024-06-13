package com.app.nperest.controller;

import com.app.nperest.domain.MemberVO;
import com.app.nperest.service.MemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/members/api")
@RequiredArgsConstructor
// http://localhost:10000/swagger-ui/index.html
@Tag(name = "Member", description = "Member API")
public class MemberAPI {
    @GetMapping("/session")
    public Map<String, Object> getSessionInfo(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        MemberVO member = (MemberVO) session.getAttribute("member");
        System.out.println("Session get: " + member); // 세션에서 회원 정보 가져오기 로그
        if (member != null) {
            response.put("loggedIn", true);
            response.put("member", member);
        } else {
            response.put("loggedIn", false);
        }

        return response;
    }
}
