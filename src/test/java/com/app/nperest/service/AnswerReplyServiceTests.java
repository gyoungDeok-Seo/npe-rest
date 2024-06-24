package com.app.nperest.service;

import com.app.nperest.domain.AnswerReplyDTO;
import com.app.nperest.domain.AnswerReplyLikeDTO;
import com.app.nperest.domain.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class AnswerReplyServiceTests {

    @Autowired
    private AnswerReplyService answerReplyService;

    @Test
    public void insertTest() {
        AnswerReplyDTO replyDTO = new AnswerReplyDTO();
        replyDTO.setAnswerId(1L);
        replyDTO.setMemberId(3L);
        replyDTO.setReplayContent("55555");

        answerReplyService.insert(replyDTO);
    }

    @Test
    public void selectReplyListTest() {
         answerReplyService.selectReplyList(1L, 3L);
    }

    @Test
    public void isLikeTest() {

        answerReplyService.isLike(1L, 3L);
    }

    @Test
    public void updateTest() {
        AnswerReplyDTO replyDTO = new AnswerReplyDTO();
        replyDTO.setId(1L);
        replyDTO.setReplayContent("123123");

        answerReplyService.update(replyDTO);
    }

    @Test
    public void deleteTest() {
        AnswerReplyDTO replyDTO = new AnswerReplyDTO();
        replyDTO.setId(1L);

        answerReplyService.delete(replyDTO);
    }

    @Test
    public void replyLikeTest() {
        AnswerReplyLikeDTO replyLikeDTO = new AnswerReplyLikeDTO();
        replyLikeDTO.setAnswerId(1L);
        replyLikeDTO.setMemberId(3L);
        replyLikeDTO.setQuestionId(1L);

        answerReplyService.replyLike(replyLikeDTO);
    }

    @Test
    public void selectReplyCountTest() {
        Long answerId = 1L;
        int replyCount = answerReplyService.selectReplyCount(answerId);
        System.out.println(replyCount);
    }
}
