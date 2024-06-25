package com.app.nperest.service;

import com.app.nperest.domain.AnswerDTO;
import com.app.nperest.domain.AnswerLikeDTO;
import com.app.nperest.domain.AnswerVO;
import com.app.nperest.domain.MemberVO;

import java.util.List;

public interface AnswerService {
    public void insert(AnswerVO answerVO);

    public List<AnswerDTO> selectAnswerList(Long id);

    public Boolean isLike(Long answerId, Long memberId);

    public void update(AnswerVO answerVO);

    public void delete(AnswerVO answerVO);

    public List<MemberVO> selectLikeUserList(AnswerVO answerVO);

    public void answerLike(AnswerLikeDTO answerLikeDTO);

    public void answerHardDelete(Long id);

    public void answerLikeHardDelete(Long id);

    public void replyHardDelete(Long id);

    public void replyLikeHardDelete(Long id);

    public void insertProfanity(String comment, boolean target);
}
