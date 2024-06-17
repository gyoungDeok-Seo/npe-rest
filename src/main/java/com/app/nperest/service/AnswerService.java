package com.app.nperest.service;

import com.app.nperest.domain.AnswerDTO;
import com.app.nperest.domain.AnswerVO;
import com.app.nperest.domain.MemberVO;
import com.app.nperest.domain.QnaDTO;

import java.util.List;
import java.util.Map;

public interface AnswerService {
    public void insert(AnswerVO answerVO);

    public List<AnswerDTO> selectList(AnswerDTO answerDTO);

    public void update(AnswerVO answerVO);

    public void delete(AnswerVO answerVO);

    public List<MemberVO> selectLikeUserList(AnswerVO answerVO);

    public boolean answerLike(AnswerVO answerVO);
}
