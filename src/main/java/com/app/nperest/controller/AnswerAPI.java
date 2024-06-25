package com.app.nperest.controller;

import com.app.nperest.domain.*;
import com.app.nperest.service.AnswerReplyService;
import com.app.nperest.service.AnswerService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/answers/api")
@RequiredArgsConstructor
public class AnswerAPI {
    private final AnswerService answerService;
    private final AnswerReplyService answerReplyService;

    @PostMapping("/create")
    public List<AnswerDTO> create(HttpSession session, @RequestBody AnswerVO answerVO) {
        MemberVO memberVO = (MemberVO) session.getAttribute("member");
        answerVO.setMemberId(memberVO.getId());

        answerService.insert(answerVO);

        return getAnswerListWithDetails(answerVO.getQuestionId(), memberVO.getId());
    }

    @GetMapping("/list")
    public List<AnswerDTO> selectQnaList(HttpSession session, Long id) {
        MemberVO memberVO = (MemberVO) session.getAttribute("member");
        return getAnswerListWithDetails(id, memberVO.getId());
    }

    @PostMapping("/update")
    public List<AnswerDTO> update(HttpSession session, @RequestBody AnswerVO answerVO) {
        MemberVO memberVO = (MemberVO) session.getAttribute("member");
        answerVO.setMemberId(memberVO.getId());
        answerService.update(answerVO);

        return getAnswerListWithDetails(answerVO.getQuestionId(), memberVO.getId());
    }

    @PostMapping("/delete")
    public List<AnswerDTO> delete(HttpSession session, @RequestBody AnswerVO answerVO) {
        MemberVO memberVO = (MemberVO) session.getAttribute("member");
        answerVO.setMemberId(memberVO.getId());

        answerService.delete(answerVO);

        return getAnswerListWithDetails(answerVO.getQuestionId(), memberVO.getId());
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

    @PostMapping("/answer-like")
    public List<AnswerDTO> answerLike(HttpSession session, @RequestBody AnswerLikeDTO answerLikeDTO) {
        MemberVO memberVO = (MemberVO) session.getAttribute("member");
        answerLikeDTO.setMemberId(memberVO.getId());

        answerService.answerLike(answerLikeDTO);

        return getAnswerListWithDetails(answerLikeDTO.getQuestionId(), memberVO.getId());
    }


    private List<AnswerDTO> getAnswerListWithDetails(Long questionId, Long memberId) {
        List<AnswerDTO> list = answerService.selectAnswerList(questionId);
        for (AnswerDTO answerDTO : list) {
            answerDTO.setMemberLiked(answerService.isLike(answerDTO.getId(), memberId));
            answerDTO.setReplyList(answerReplyService.selectReplyList(answerDTO.getId(), memberId));
            answerDTO.setMaster(Objects.equals(answerDTO.getMemberId(), memberId));
        }
        return list;
    }

    @DeleteMapping("/hard-delete")
    public void answerHardDelete(@RequestBody AnswerDTO answerDTO) {
        answerService.insertProfanity(answerDTO.getAnswerContent(), true);
        answerService.replyLikeHardDelete(answerDTO.getId());
        answerService.replyHardDelete(answerDTO.getId());
        answerService.answerLikeHardDelete(answerDTO.getId());
        answerService.answerHardDelete(answerDTO.getId());
    }
}
