package com.app.nperest.controller;

import com.app.nperest.domain.AnswerVO;
import com.app.nperest.domain.MemberVO;
import com.app.nperest.service.AnswerService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/answers/api")
@RequiredArgsConstructor
public class AnswerAPI {
    private final AnswerService answerService;

    @PostMapping("/create")
    public Map<String, Object> create(HttpSession session, @RequestBody AnswerVO answerVO) {
        MemberVO memberVO = (MemberVO) session.getAttribute("member");
        answerVO.setMemberId(memberVO.getId());
        /* 답글 등록
        json으로 보내야 하는 변수명 : 실제 필요값
        answerContent : answerContent
        questionId : questionId
        */
        answerService.insert(answerVO);
        Map<String, Object> response = new HashMap<>();
        response.put("successMsg", true);
        return response;
    }

    @PostMapping("/update")
    public Map<String, Object> update(HttpSession session, @RequestBody AnswerVO answerVO) {
        MemberVO memberVO = (MemberVO) session.getAttribute("member");
        answerVO.setMemberId(memberVO.getId());

        /* 답글 수정
        json으로 보내야 하는 변수명 : 실제 필요값
        answerContent : answerContent
        id : answerId
        */

        answerService.update(answerVO);

        Map<String, Object> response = new HashMap<>();
        response.put("successMsg", true);
        return response;
    }

    @PostMapping("/delete")
    public Map<String, Object> delete(HttpSession session, @RequestBody AnswerVO answerVO) {
        MemberVO memberVO = (MemberVO) session.getAttribute("member");
        answerVO.setMemberId(memberVO.getId());

        /* 답글 삭제
        json으로 보내야 하는 변수명 : 실제 필요값
        id : answerId
        */

        answerService.delete(answerVO);

        Map<String, Object> response = new HashMap<>();
        response.put("successMsg", true);
        return response;
    }

    @PostMapping("/likeList")
    public Map<String, Object> likeList(HttpSession session, @RequestBody AnswerVO answerVO) {
        /* 답글 좋아요 사용자 리스트 조회
        json으로 보내야 하는 변수명 : 실제 필요값
        id : answerId
        */
        List<MemberVO> list = answerService.selectLikeUserList(answerVO);
        Map<String, Object> response = new HashMap<>();
        response.put("successMsg", true);
        response.put("data", list);
        return response;
    }

    @PostMapping("/answerLike")
    public Map<String, Object> answerLike(HttpSession session, @RequestBody AnswerVO answerVO) {
        MemberVO memberVO = (MemberVO) session.getAttribute("member");
        answerVO.setMemberId(memberVO.getId());

        boolean status = answerService.answerLike(answerVO);
        Map<String, Object> response = new HashMap<>();
        response.put("successMsg", true);
        response.put("status", status);
        return response;
    }
}
