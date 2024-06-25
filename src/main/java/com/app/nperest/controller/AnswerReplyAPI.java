package com.app.nperest.controller;

import com.app.nperest.domain.*;
import com.app.nperest.service.AnswerReplyService;
import com.app.nperest.service.AnswerService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/replies/api")
@RequiredArgsConstructor
public class AnswerReplyAPI {
    private final AnswerReplyService answerReplyService;
    private final AnswerService answerService;

    @PostMapping("/create")
    public List<AnswerDTO> create(HttpSession session, @RequestBody AnswerReplyDTO answerReplyDTO) {
        MemberVO memberVO = (MemberVO) session.getAttribute("member");
        answerReplyDTO.setMemberId(memberVO.getId());

        answerReplyService.insert(answerReplyDTO);

        return getAnswerListWithDetails(answerReplyDTO.getQuestionId(), memberVO.getId());
    }

    @PostMapping("/update")
    public List<AnswerDTO> update(HttpSession session, @RequestBody AnswerReplyDTO answerReplyDTO) {
        MemberVO memberVO = (MemberVO) session.getAttribute("member");
        answerReplyDTO.setMemberId(memberVO.getId());


        answerReplyService.update(answerReplyDTO);

        return getAnswerListWithDetails(answerReplyDTO.getQuestionId(), memberVO.getId());
    }

    @PostMapping("/delete")
    public List<AnswerDTO> delete(HttpSession session, @RequestBody AnswerReplyDTO answerReplyDTO) {
        MemberVO memberVO = (MemberVO) session.getAttribute("member");
        answerReplyDTO.setMemberId(memberVO.getId());
        answerReplyService.delete(answerReplyDTO);

        return getAnswerListWithDetails(answerReplyDTO.getQuestionId(), memberVO.getId());
    }

    @PostMapping("/likeList")
    public Map<String, Object> likeList(HttpSession session, @RequestBody AnswerReplyVO answerReplyVO) {
        List<MemberVO> list = answerReplyService.selectLikeUserList(answerReplyVO);
        Map<String, Object> response = new HashMap<>();
        response.put("successMsg", true);
        response.put("data", list);
        System.out.println(response);
        return response;
    }

    @PostMapping("/reply-like")
    public List<AnswerDTO> answerLike(HttpSession session, @RequestBody AnswerReplyLikeDTO answerReplyLikeDTO) {
        MemberVO memberVO = (MemberVO) session.getAttribute("member");
        answerReplyLikeDTO.setMemberId(memberVO.getId());

        answerReplyService.replyLike(answerReplyLikeDTO);

        return getAnswerListWithDetails(answerReplyLikeDTO.getQuestionId(), memberVO.getId());
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

    @PostMapping("/create-profanity")
    public void insertProfanity(@RequestBody ReplyAi replyAi) {
        answerReplyService.insertProfanity(replyAi);
    }

    @DeleteMapping("/hard-delete")
    public void replyHardDelete(@RequestBody AnswerReplyDTO answerReplyDTO) {
        answerReplyService.insertProfan(answerReplyDTO.getReplayContent(), true);
        answerReplyService.replyLikeHardDelete(answerReplyDTO.getId());
        answerReplyService.replyHardDelete(answerReplyDTO.getId());
    }
}
