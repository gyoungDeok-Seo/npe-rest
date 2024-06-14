package com.app.nperest.controller;

import com.app.nperest.domain.QnaDTO;
import com.app.nperest.service.QnaService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//@RestController:
//restful 컨트롤러임을 나타낸다. 이 클래스의 메서드들은 json으로 데이터를  반환
//@RequiredArgsConstructor
//lombok 라이브러리 어노테이션, final로 선언된 모든 필드의 생성자를 자동으로 생성
@RestController
@RequestMapping("/qnas/api")
@RequiredArgsConstructor
public class QnaAPI {
    private final QnaService qnaService;

    @PostMapping("/create")
    public Map<String, Object> createQna(HttpSession sesstion, @RequestBody QnaDTO qnaDTO) {
//        결과를 isOk 변수에 저장,
//        insert 메소드는 성공 시 양수를 반환하고, 실패 시 0이나 음수를 반환
        qnaService.insert(qnaDTO);
//        저장할 빈 HashMap 객체를 생성
//        자바스크립트의 객체 느낌
        Map<String, Object> response = new HashMap<>();
        response.put("successMsg", true);
        return response;
    }
}
