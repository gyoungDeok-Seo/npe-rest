package com.app.nperest.controller;

import com.app.nperest.domain.AnswerReplyVO;
import com.app.nperest.domain.AnswerVO;
import com.app.nperest.domain.MemberVO;
import com.app.nperest.service.AnswerReplyService;
import com.app.nperest.service.AnswerService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/replies/api")
@RequiredArgsConstructor
public class AnswerReplyAPI {
    private final AnswerReplyService answerReplyService;

    @PostMapping("/create")
    public Map<String, Object> create(HttpSession session, @RequestBody AnswerReplyVO answerReplyVO) {
        MemberVO memberVO = (MemberVO) session.getAttribute("member");
        answerReplyVO.setMemberId(memberVO.getId());
        /* 답글 등록
        json으로 보내야 하는 변수명 : 실제 필요값
        replyContent : answerContent
        answerId : questionId
        */
        answerReplyService.insert(answerReplyVO);
        Map<String, Object> response = new HashMap<>();
        response.put("successMsg", true);
        return response;
    }

    @PostMapping("/update")
    public Map<String, Object> update(HttpSession session, @RequestBody AnswerReplyVO answerReplyVO) {
        MemberVO memberVO = (MemberVO) session.getAttribute("member");
        answerReplyVO.setMemberId(memberVO.getId());

        /* 답글 수정
        json으로 보내야 하는 변수명 : 실제 필요값
        replyContent : answerContent
        id : answerId
        */

        answerReplyService.update(answerReplyVO);

        Map<String, Object> response = new HashMap<>();
        response.put("successMsg", true);
        return response;
    }

    @PostMapping("/delete")
    public Map<String, Object> delete(HttpSession session, @RequestBody AnswerReplyVO answerReplyVO) {
        MemberVO memberVO = (MemberVO) session.getAttribute("member");
        answerReplyVO.setMemberId(memberVO.getId());
        System.out.println(answerReplyVO);
        /* 답글 삭제
        json으로 보내야 하는 변수명 : 실제 필요값
        id : answerId
        */

        answerReplyService.delete(answerReplyVO);

        Map<String, Object> response = new HashMap<>();
        response.put("successMsg", true);
        return response;
    }

    @PostMapping("/likeList")
    public Map<String, Object> likeList(HttpSession session, @RequestBody AnswerReplyVO answerReplyVO) {
        /* 답글 좋아요 사용자 리스트 조회
        json으로 보내야 하는 변수명 : 실제 필요값
        id : answerId
        */
        List<MemberVO> list = answerReplyService.selectLikeUserList(answerReplyVO);
        Map<String, Object> response = new HashMap<>();
        response.put("successMsg", true);
        response.put("data", list);
         System.out.println(response);
        return response;
    }

    @PostMapping("/replyLike")
    public Map<String, Object> answerLike(HttpSession session, @RequestBody AnswerReplyVO answerReplyVO) {
        MemberVO memberVO = (MemberVO) session.getAttribute("member");
        answerReplyVO.setMemberId(memberVO.getId());

        boolean status = answerReplyService.replyLike(answerReplyVO);

        Map<String, Object> response = new HashMap<>();
        response.put("successMsg", true);
        response.put("status", status);
        return response;
    }
}
