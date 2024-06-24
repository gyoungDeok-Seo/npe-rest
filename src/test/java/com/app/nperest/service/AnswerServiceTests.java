package com.app.nperest.service;

import com.app.nperest.domain.AnswerDTO;
import com.app.nperest.domain.AnswerLikeDTO;
import com.app.nperest.domain.AnswerVO;
import com.app.nperest.domain.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class AnswerServiceTests {

    @Autowired
    private AnswerService answerService;

    @Test
    public void insertTest() {
        AnswerVO answerVO = new AnswerVO();
        answerVO.setAnswerContent("5675");
        answerVO.setMemberId(3L);
        answerVO.setQuestionId(1L);

        answerService.insert(answerVO);
    }

    @Test
    public void selectAnswerListTest() {
        Long questionId = 1L; // 조회할 질문의 ID
        answerService.selectAnswerList(questionId);
    }

    @Test
    public void isLikeTest() {
        Long answerId = 1L;
        Long memberId = 3L;
        answerService.isLike(answerId, memberId);
    }

    @Test
    public void updateTest() {
        AnswerVO answerVO = new AnswerVO();
        answerVO.setId(1L);
        answerVO.setAnswerContent("222222");
        answerService.update(answerVO);
    }

    @Test
    public void deleteTest() {
        AnswerVO answerVO = new AnswerVO();
        answerVO.setId(1L);
        answerService.delete(answerVO);
    }

    @Test
    public void answerLikeTest() {
        AnswerLikeDTO answerLikeDTO = new AnswerLikeDTO();
        answerLikeDTO.setAnswerId(1L);
        answerLikeDTO.setMemberId(3L);
        answerLikeDTO.setQuestionId(1L);

        answerService.answerLike(answerLikeDTO);
    }
}